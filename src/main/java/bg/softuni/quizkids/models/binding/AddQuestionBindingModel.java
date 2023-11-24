package bg.softuni.quizkids.models.binding;

import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.validation.anotations.AnswerLength;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class AddQuestionBindingModel {
    @NotBlank
    @Size(min = 3, message = "Question length must be more than 3 characters!")
    private String content;
//    @AnswerLength(min = 2, max = 50, message = "Answer length must be between 2 and 50 characters")
    private List<AddAnswerBindingModel> answers = new ArrayList<>();

    public void createAnswersList(){
        for (int i = 0; i < 6; i++) {
            this.answers.add(new AddAnswerBindingModel(" "));
        }
    }
}
