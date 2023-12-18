package bg.softuni.quizkids.models.binding;

import bg.softuni.quizkids.validation.anotations.ConfirmPasswordMatches;
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
public class UserEditProfileBindingModel {
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
