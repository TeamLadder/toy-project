package team.radder.toychattingproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login() {
        return "login2";
    }

    //한 html에서 하므로 필요없음
    @GetMapping("/signup")
    public String signup() {
        return "login2";
    }
}