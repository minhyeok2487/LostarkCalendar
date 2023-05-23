package lostark.calendar.web;

import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.users.dtos.UserJoinRequest;
import lostark.calendar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
        return ResponseEntity.ok().body("회원가입 성공");
    }
}
