package lostark.calendar.service;

import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.marketItems.MarketItem;
import lostark.calendar.domain.marketItems.MarketItemRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    @Value("${Lostark-API-Key}")
    private String LostarkApiKey;
    private final ApiService apiService;
    private final MarketItemRepository marketItemRepository;
    public JSONObject getMarketCategories(int CategoryCode) {
        String link = "https://developer-lostark.game.onstove.com/markets/items/";
        try {
            String parameter = "{\n"
                    + "  \"Sort\": \"RECENT_PRICE\",\n"
                    + "  \"CategoryCode\": "+CategoryCode+",\n"
                    + "  \"PageNo\": 1,\n"
                    + "  \"SortCondition\": \"DESC\"\n"
                    + "}";
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(apiService.postApi(link, parameter, LostarkApiKey));
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<MarketItem> getDBMarketItems() {
        return marketItemRepository.findAll();
    }
}
