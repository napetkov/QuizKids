package bg.softuni.quizkids.models.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
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
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    private UserEntity author;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Answer> answers;
}
