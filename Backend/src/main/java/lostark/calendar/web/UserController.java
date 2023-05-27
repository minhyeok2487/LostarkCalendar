package lostark.calendar.web;

import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.users.User;
import lostark.calendar.domain.users.dtos.UserJoinRequest;
import lostark.calendar.domain.users.dtos.UserLoginRequest;
import lostark.calendar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest joinRequest) {
        userService.join(joinRequest);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResponseEntity.ok().body("로그인 성공 / 토큰 :" + token);
    }

}
