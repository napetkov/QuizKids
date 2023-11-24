package bg.softuni.quizkids.models.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAnswerBindingModel {
    @NotBlank
    @Size(min = 3, message = "Question length must be more than 3 characters!")
    String content;
}
