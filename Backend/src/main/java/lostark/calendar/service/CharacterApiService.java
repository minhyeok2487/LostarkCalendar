package lostark.calendar.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class CharacterApiService {
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
