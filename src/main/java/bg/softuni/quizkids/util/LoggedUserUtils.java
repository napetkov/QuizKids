package bg.softuni.quizkids.util;

import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class LoggedUserUtils {

    public static String getLoggedInUsername(){
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();

        if(authenticator != null && authenticator.isAuthenticated()){
                return authenticator.getName();
        }
        return null;
    }

}
