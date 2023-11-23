package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
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

    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("addQuestionBindingModel")){
            model.addAttribute("addQuestionBindingModel",new AddQuestionBindingModel());
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

        return "redirect:/questions/all";
    }
}
