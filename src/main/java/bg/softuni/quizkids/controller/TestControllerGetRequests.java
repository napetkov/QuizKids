package bg.softuni.quizkids.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestControllerGetRequests {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/math")
    public String math(){
        return "math";
    }

    @GetMapping("/add-question")
    public String add(){
        return "add-question";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}


