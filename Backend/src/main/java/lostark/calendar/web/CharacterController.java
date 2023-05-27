package lostark.calendar.web;


import lombok.RequiredArgsConstructor;
import lostark.calendar.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
	public ResponseEntity<?> getCharacter(@PathVariable String characterName, Authentication authentication) {
		return new ResponseEntity<>(new CMRespDto<>(1, "캐릭터 정보 불러오기 성공", characterService.Characters(characterName)), HttpStatus.OK);
	}

}
