package bg.softuni.quizkids.models.binding;

import bg.softuni.quizkids.validation.anotations.ConfirmPasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ConfirmPasswordMatches(message = "Confirm password do not match!")
public class UserChangePasswordBindingModel {
    @NotBlank
    @Size(min = 3, message = "Password length must be more than 3 characters")
    private String oldPassword;
    @NotBlank
    @Size(min = 3, message = "Password length must be more than 3 characters")
    private String password;
    @Size(min = 3, message = "Password length must be more than 3 characters")
    private String confirmPassword;

}
