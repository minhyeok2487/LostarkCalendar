package lostark.calendar.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostark.calendar.exception.AppException;
import lostark.calendar.exception.ErrorCode;
import lostark.calendar.service.UserService;
import lostark.calendar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization이 잘못되었습니다");
            filterChain.doFilter(request, response);
            throw new AppException(ErrorCode.INVALID_AUTH_TOKEN, "authorization이 잘못되었습니다");
        }

        // Token 꺼내기
        String token = authorization.split(" ")[1];

        // Token Expired되었는지 여부
        if(JwtUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            throw new AppException(ErrorCode.INVALID_AUTH_TOKEN, "만료된 토큰입니다");
        }

        // Token에서 UserName 꺼내기
        String userName = JwtUtil.getUserName(token, secretKey);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
