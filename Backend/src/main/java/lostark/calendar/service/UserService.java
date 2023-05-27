package lostark.calendar.service;

import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.users.User;
import lostark.calendar.domain.users.UserRepository;
import lostark.calendar.domain.users.dtos.UserJoinRequest;
import lostark.calendar.domain.users.dtos.UserLoginRequest;
import lostark.calendar.exception.AppException;
import lostark.calendar.exception.ErrorCode;
import lostark.calendar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMS = 1000 * 60 * 60L;

    public String join(UserJoinRequest dto) {
        // userName 중복체크
        userRepository.findByUsername(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED,
                            user.getUsername() + "은(는) 이미 존재하는 아이디입니다.");
                });

        // 저장
        User user = User.builder()
                .username(dto.getUserName())
                .password(encoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);
        return "SUCCESS";
    }

    public String login(UserLoginRequest loginRequest) {
        // userName 없음
        User user = userRepository.findByUsername(loginRequest.getUserName())
                .orElseThrow(() ->
                        new AppException(ErrorCode.USERNAME_NOT_FOUND,
                                loginRequest.getUserName() + "이 없습니다."));
        // password 틀림
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.USERNAME_PASSWORD, "패스워드 틀림");
        }
        System.out.println("user = " + user);
        // 로그인 성공 => 토큰 발행
        String token = JwtUtil.createToken(loginRequest.getUserName(), key, expireTimeMS);
        return token;

    }
}
