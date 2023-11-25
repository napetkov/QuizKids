package bg.softuni.quizkids.models.dto;

import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.Category;
import bg.softuni.quizkids.models.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class QuestionsDTO {
    private long id;
    private String content;
    private LocalDate createdOn;
    private String categoryName;
    private String authorUsername;
//    private List<Answer> answers;
}
