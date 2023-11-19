package bg.softuni.quizkids.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends BaseEntity{
    @Column(name = "content",nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDate createdOn;
    @ManyToOne
    private UserEntity author;
    @OneToMany
    private Set<Answer> answers;
}
