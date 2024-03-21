package team.radder.toychattingproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import team.radder.toychattingproject.domain.dto.AddUserRequest;
import team.radder.toychattingproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);  // 회원 가입(저장)
        //**여기서 이거 무조건 해줘야함. void이런거 안됨. return 으로 페이지 해야함. 안해주면 오류**
        return "redirect:/login";   // 회원 가입 처리 후 로그인 페이지로 강제 이동
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "login";
    }
}