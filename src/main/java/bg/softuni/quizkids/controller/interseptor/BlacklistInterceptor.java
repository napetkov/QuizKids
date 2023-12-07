package bg.softuni.quizkids.controller.interseptor;

import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.util.LoggedUserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
@Component
public class BlacklistInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    public BlacklistInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = LoggedUserUtils.getLoggedInUsername();
        if(username.equals("anonymousUser")){
            return true;
        }

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with username " + username + " was not found!");
        }

        UserEntity user = userOptional.get();

        boolean isBlacklisted = user.getRole().getName() == UserRole.BLACKLISTED;
        if (request.getRequestURI().equals("/blacklisted")) {
            return true;
        }

        if(isBlacklisted){
            response.sendRedirect("/blacklisted");
            System.out.println("BLACKLISTED");

            return false;
        }
        return true;
    }
}
