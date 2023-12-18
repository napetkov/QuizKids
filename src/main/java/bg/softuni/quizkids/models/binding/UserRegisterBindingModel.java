package bg.softuni.quizkids.models.binding;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.validation.anotations.ConfirmPasswordMatches;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ConfirmPasswordMatches(message = "Confirm password do not match!")
public class UserRegisterBindingModel extends ConfirmPasswordBindingModel{
    @NotBlank
    @Size(min = 3, message = "Username length must be more than 3 characters!")
    private String username;
//    @NotBlank
//    @Size(min = 3, message = "Password length must be more than 3 characters")
//    private String password;
//    @Size(min = 3, message = "Password length must be more than 3 characters")
//    private String confirmPassword;
    @NotBlank
    @Size(min = 3, message = "First name length must be more than 3 characters")
    private String firstName;
    private String lastName;
    @Positive
    private int age;
    @NotBlank
    @Email
    private String email;
    private String city;

}
