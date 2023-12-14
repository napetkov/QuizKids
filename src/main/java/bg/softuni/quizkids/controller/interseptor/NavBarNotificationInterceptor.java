package bg.softuni.quizkids.controller.interseptor;

import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.services.MessageService;
import bg.softuni.quizkids.services.NotificationService;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NavBarNotificationInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private final NotificationService notificationService;
    private final MessageService messageService;

    public NavBarNotificationInterceptor(UserService userService, NotificationService notificationService, MessageService messageService) {
        this.userService = userService;
        this.notificationService = notificationService;
        this.messageService = messageService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = LoggedUserUtils.getLoggedInUsername();

        if (!username.equals("anonymousUser")) {
            UserEntity user = userService.getLoggedUser();
            long countOfUnreadNotifications = notificationService.countByUserIdAndIsRead(user.getId());
            long countOfUnreadMessages = messageService.countByUserIdAndIsRead();
            request.setAttribute("unreadNotificationCount", countOfUnreadNotifications);
            request.setAttribute("unreadMessagesCount", countOfUnreadMessages);
        }

        return true;
    }
}
