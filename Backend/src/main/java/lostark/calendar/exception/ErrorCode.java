package lostark.calendar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERNAME_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),

    // 403 : 서버가 클라이언트의 접근을 거부
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 없습니다");

    private HttpStatus httpStatus;
    private String message;

}
