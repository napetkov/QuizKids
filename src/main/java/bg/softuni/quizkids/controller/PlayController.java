package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.services.PlayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/play")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping("/questions/{questionId}")
    public String playQuestionById(@PathVariable long questionId, Model model){

        model.addAttribute("questionAndAnswersDTO",
                playService.findQuestionByIdToQuestionAndAnswerDTO(questionId));

        return "play";
    }

    @GetMapping("/{categoryName}")
    public String play(@PathVariable("categoryName")CategoryName categoryName){

        return "play";
    }
    @GetMapping("/all")
    public String playAll(Model model){

            model.addAttribute("questionAndAnswersDTO", playService.getRandomQuestionFromAll());

        return "play";
    }

    @PostMapping("/all")
    public String answerQuestion(@RequestParam Long questionId,
                                 @RequestParam boolean answerCorrect, Model model){
        if(answerCorrect){
            playService.correctlyAnsweringOfQuestion(questionId);
            return "correct-answer";
        }

        model.addAttribute("questionId",questionId);

        return "incorrect-answer";
    }
}
