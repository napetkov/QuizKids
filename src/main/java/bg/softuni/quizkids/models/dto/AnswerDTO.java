package bg.softuni.quizkids.models.dto;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO extends AddAnswerBindingModel {
    private long id;
    private boolean isCorrect;
}
