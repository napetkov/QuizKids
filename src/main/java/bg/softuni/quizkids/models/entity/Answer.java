package bg.softuni.quizkids.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="answers")
public class Answer extends BaseEntity{
    private String content;
    private LocalDate createdOn;
    private boolean isCorrect;
    @ManyToOne
    private UserEntity author;
    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;
}
