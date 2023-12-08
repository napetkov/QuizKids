package bg.softuni.quizkids.controller.interseptor;

import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
public class BlacklistInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public BlacklistInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String username = LoggedUserUtils.getLoggedInUsername();

        if (username.equals("anonymousUser")) {
            return true;
        }

        UserEntity user = userService.getLoggedUser();

        if (request.getRequestURI().equals("/blacklisted")) {
            return true;
        }

        boolean isBlacklisted = user.getRole().getName() == UserRole.BLACKLISTED;
        if (isBlacklisted) {
            response.sendRedirect("/blacklisted");
            return false;
        }

        return true;
    }
}

