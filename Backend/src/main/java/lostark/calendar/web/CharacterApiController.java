package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lostark.calendar.service.CharacterApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class CharacterApiController {

	private final CharacterApiService characterApiService;
	@GetMapping("api/character/{characterName}")
	public ArrayList getCharacter(@PathVariable String characterName) {
		return characterApiService.Characters(characterName);
	}

}
