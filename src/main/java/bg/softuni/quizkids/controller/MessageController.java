package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.services.MessageService;
import bg.softuni.quizkids.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public String allNotifications(Model model){

        model.addAttribute("getAllMessages", messageService.getAllMessages());

        return "message";

    }

    @PostMapping("/message/{id}")
    public String makeNotificationRead(@PathVariable("id") Long id, Model model){

        if(!model.containsAttribute("getAllMessages")){
            model.addAttribute("getAllMessages", messageService.getAllMessages());
        }

        messageService.readMessage(id);

        return "redirect:/notification";

    }
}
