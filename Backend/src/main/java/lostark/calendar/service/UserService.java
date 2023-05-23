package lostark.calendar.service;

import lombok.RequiredArgsConstructor;
import lostark.calendar.domain.users.User;
import lostark.calendar.domain.users.UserRepository;
import lostark.calendar.domain.users.dtos.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(UserJoinRequest dto) {
        // UserJoinRequest. userName 중복체크
        userRepository.findByUsername(dto.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException("이미 존재하는 아이디입니다.");
                });

        // 저장
        User user = User.builder()
                .username(dto.getUserName())
                .password(dto.getPassword())
                .build();
        userRepository.save(user);
        return "SUCCESS";
    }
}
