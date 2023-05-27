package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostark.calendar.domain.marketItems.MarketItem;
import lostark.calendar.exception.AppException;
import lostark.calendar.service.MarketService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/market")
@Slf4j
public class MarketController {

	private final MarketService marketService;

	@GetMapping("/category/{categoryCode}")
	public ResponseEntity<?> getMarket(@PathVariable int categoryCode) {
		return new ResponseEntity<>(new CMRespDto<>(1, categoryCode+" 불러오기", marketService.getMarketCategories(categoryCode)), HttpStatus.OK);
	}

	@GetMapping("/db")
	public ResponseEntity<?> getDBMarket() {
		List<MarketItem> dbMarketItems = marketService.getDBMarketItems();
		return new ResponseEntity<>(new CMRespDto<>(1, "DB 데이터 불러오기 성공", dbMarketItems), HttpStatus.OK);
	}


}
