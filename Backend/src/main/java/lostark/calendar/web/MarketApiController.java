package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.marketItems.MarketItem;
import lostark.calendar.service.MarketApiService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MarketApiController {

	private final MarketApiService marketApiService;
	@GetMapping("api/market/{categoryCode}")
	public JSONObject getMarket(@PathVariable int categoryCode) {
		return marketApiService.getMarketCategories(categoryCode);
	}

	@GetMapping("api/db/market")
	public List<MarketItem> getDBMarket() {
		return marketApiService.getDBMarketItems();
	}

}
