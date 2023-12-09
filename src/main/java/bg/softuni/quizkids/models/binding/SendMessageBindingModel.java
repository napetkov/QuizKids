package bg.softuni.quizkids.models.binding;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageBindingModel {
    @NotBlank
    @Size(min = 3)
    private String name;
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 10)
    private String text;
}
