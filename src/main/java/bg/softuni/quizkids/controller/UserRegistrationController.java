package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {
    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegisterBindingModel userRegisterBindingModel){
        userService.registerUser(userRegisterBindingModel);

        return "redirect:/login";
    }

}
