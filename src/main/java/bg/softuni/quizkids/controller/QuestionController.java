package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }



    @GetMapping("/add")
    public String add(Model model){

        if(!model.containsAttribute("addQuestionBindingModel")){

            AddQuestionBindingModel attributeValue = new AddQuestionBindingModel();
            attributeValue.createAnswersList();

            model.addAttribute("addQuestionBindingModel", attributeValue);
        }

        return "add-question";
    }

    @PostMapping("/add")
    public String add(@Valid AddQuestionBindingModel addQuestionBindingModel,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addQuestionBindingModel", addQuestionBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.addQuestionBindingModel",
                    bindingResult);
            return "redirect:/question/add";
        }

        questionService.addQuestion(addQuestionBindingModel, LoggedUserUtils.getLoggedInUsername());

        return "redirect:/question/all";
    }

    @GetMapping("/all")
    public String all(){
        return "question-all";
    }
}
