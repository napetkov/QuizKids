package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.services.NotificationService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification")
    public String allNotifications(Model model){

        model.addAttribute("getAllNotifications", notificationService.getAllNotifications());

        return "notification";

    }

    @PostMapping("/notification/{id}")
    public String makeNotificationRead(@PathVariable("id") Long id, Model model){

        if(!model.containsAttribute("getAllNotifications")){
            model.addAttribute("getAllNotifications", notificationService.getAllNotifications());
        }

        notificationService.readNotification(id);

        return "redirect:/notification";

    }
}
