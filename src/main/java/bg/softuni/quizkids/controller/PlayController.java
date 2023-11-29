package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.enums.CategoryName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/play")
public class PlayController {

    @GetMapping("/{categoryName}")
    public String play(@PathVariable("categoryName")CategoryName categoryName){

        return "math";
    }

}
