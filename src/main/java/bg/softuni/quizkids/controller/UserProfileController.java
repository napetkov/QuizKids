package bg.softuni.quizkids.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserProfileController {

    @GetMapping("/profile")
    public String getUserProfiole(){
        return "profile";
    }
}
