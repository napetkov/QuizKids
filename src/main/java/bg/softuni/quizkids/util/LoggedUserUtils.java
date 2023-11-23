package bg.softuni.quizkids.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class LoggedUserUtils {

    private LoggedUserUtils(){
    }

    public static String getLoggedInUsername(){
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();

        if(authenticator != null && authenticator.isAuthenticated()){
                return authenticator.getName();
        }
        return null;
    }
}
