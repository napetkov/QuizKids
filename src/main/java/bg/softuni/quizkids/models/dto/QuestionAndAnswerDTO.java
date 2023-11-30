package bg.softuni.quizkids.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class QuestionAndAnswerDTO extends QuestionDTO{
    private List<AnswerDTO> answers;
}
