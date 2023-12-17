package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserProfileController {
    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public String getUserProfile(Model model){

        model.addAttribute("loggedUserProfileInfo",userService.loggedUserProfileInfo());

        return "profile";
    }
}
