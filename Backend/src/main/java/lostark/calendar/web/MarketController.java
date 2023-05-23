package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.marketItems.MarketItem;
import lostark.calendar.service.MarketService;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/market")
public class MarketController {

	private final MarketService marketService;

	@GetMapping("/category/{categoryCode}")
	public ResponseEntity<JSONObject> getMarket(@PathVariable int categoryCode) {
		return ResponseEntity.ok().body(marketService.getMarketCategories(categoryCode));
	}

	@GetMapping("/db")
	public ResponseEntity<List<MarketItem>> getDBMarket() {
		return ResponseEntity.ok().body(marketService.getDBMarketItems());
	}

}
