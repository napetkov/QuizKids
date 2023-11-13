package bg.softuni.quizkids.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @ManyToOne
    private User author;
    @ManyToOne
    private Question question;
}
