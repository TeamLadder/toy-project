package team.radder.toychattingproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import team.radder.toychattingproject.domain.Role;
import team.radder.toychattingproject.security.CustomAuthenticationFailureHandler;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

//Web보안 활성화
@EnableWebSecurity
//해당 클래스가 스프링의 구성 클래스임을 나타냄
@Configuration
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer configure() {      // 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring().requestMatchers(toH2Console())
                .requestMatchers("/static/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성, filterChain() 메서드 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->              // 인증, 인가 설정
//                        auth.requestMatchers("/**").permitAll()
                        auth.requestMatchers("/login", "/user").permitAll()
                                .requestMatchers("/admin/**", "/api/admin/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated())
                .formLogin(auth -> auth.loginPage("/login")     // 폼 기반 로그인 설정
                        //찾아보니 이거 하니까 username으로 안받아도 되고 설정한 파라미터로 받을 수 있음.
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/chatting")
                        .failureHandler(new CustomAuthenticationFailureHandler()) // 실패 핸들러 등록
                )
                .logout(auth -> auth.logoutSuccessUrl("/login") // 로그아웃 설정
                        .invalidateHttpSession(true))
                .csrf(auth -> auth.disable());                  // csrf 비활성화
        return httpSecurity.build();
    }

    // 패스워드 인코더로 사용할 빈 등록 , 비밀번호 해쉬화 해줌.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
