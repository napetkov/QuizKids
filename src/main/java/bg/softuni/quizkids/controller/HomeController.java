package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;
import bg.softuni.quizkids.services.MessageService;
import bg.softuni.quizkids.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    private final UserService userService;
    private final MessageService messageService;

    public HomeController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("categoriesOfNotAnsweredQuestions", userService.getCategoriesOfNotAnsweredQuestions());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        if(!model.containsAttribute("sendMessageBindingModel")){
            model.addAttribute("sendMessageBindingModel", new SendMessageBindingModel());
        }
        return "contact";
    }

    @PostMapping("/contact")
    public String sendMessageToContact(@Valid SendMessageBindingModel sendMessageBindingModel,
                                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("sendMessageBindingModel", sendMessageBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sendMessageBindingModel",
                    bindingResult);
            return "redirect:/contact";
        }

        messageService.sendMessageToContactWithUs(sendMessageBindingModel);

        return "redirect:/";
    }
}
