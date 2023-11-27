package bg.softuni.quizkids.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/play")
public class PlayController {

    @GetMapping("/math")
    public String play(){
        return "math";
    }

}
