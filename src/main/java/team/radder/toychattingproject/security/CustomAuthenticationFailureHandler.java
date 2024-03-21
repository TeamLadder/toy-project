package team.radder.toychattingproject.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException exception) throws IOException {
        // 로그인 실패 이유 출력 또는 로그에 남기기
        System.out.println("로그인 실패 이유: " + exception.getMessage());

        // 실패한 이유에 따라 적절한 처리를 추가할 수 있습니다.
        // 여기서는 다시 로그인 페이지로 리다이렉트하도록 설정합니다.
        response.sendRedirect("/login?error");
    }
}
