package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.services.PlayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/play")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping("/{categoryName}")
    public String play(@PathVariable("categoryName")CategoryName categoryName){


        return "math";
    }
    @GetMapping("/all")
    public String playAll(Model model){

        model.addAttribute("questionAndAnswersDTO", playService.getRandomQuestionFromAll());

        return "math";
    }
}
