package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.NotificationRepository;
import bg.softuni.quizkids.services.NotificationService;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final UserService userService;
    private final NotificationService notificationService;
    public GlobalControllerAdvice(UserService userService , NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @ModelAttribute("unreadNotificationCount")
    public long getUnreadNotificationCount() {
        String username = LoggedUserUtils.getLoggedInUsername();
        UserEntity user = userService.getLoggedUser();
        return notificationService.countByUserIdAndIsRead(user.getId());
    }

}
