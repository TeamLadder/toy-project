package team.radder.toychattingproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/signin")
    public String login() {
        return "login";
    }
}
