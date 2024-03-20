package team.radder.toychattingproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingViewController {
    @GetMapping("/chatting")
    public String login() {
        return "chatting";
    }
}
