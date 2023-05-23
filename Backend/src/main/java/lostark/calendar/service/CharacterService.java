package lostark.calendar.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class CharacterService {
	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;
	private final ApiService apiService;

	public JSONArray Characters(String characterName) {
		try {
			characterName = URLEncoder.encode(characterName, "UTF-8");
			String link = "https://developer-lostark.game.onstove.com/characters/"+characterName+"/siblings";
			InputStreamReader api = apiService.getApi(link, LostarkApiKey);
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(api);
			return array;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
