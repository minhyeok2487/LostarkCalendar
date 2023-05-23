package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lostark.calendar.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/character")
public class CharacterController {

	private final CharacterService characterService;
	@GetMapping("/{characterName}")
	public ResponseEntity<ArrayList> getCharacter(@PathVariable String characterName) {
		return ResponseEntity.ok().body(characterService.Characters(characterName));
	}

}
