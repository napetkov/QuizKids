package bg.softuni.quizkids.exceptions;

import lombok.Getter;

@Getter
public class UserNotUniqueException extends RuntimeException{
    private final String username;

    public UserNotUniqueException(String username) {
        super("There is already a registered User with username:" + username + "!");
        this.username = username;
    }
}
