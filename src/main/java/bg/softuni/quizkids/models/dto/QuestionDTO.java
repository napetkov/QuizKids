package bg.softuni.quizkids.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO {
    private long id;
    private String content;
    private LocalDate createdOn;
    private String categoryName;
    private String authorUsername;
//    private List<Answer> answers;
}
