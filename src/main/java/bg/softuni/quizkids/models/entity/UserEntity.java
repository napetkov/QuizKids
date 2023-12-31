package bg.softuni.quizkids.models.entity;

import bg.softuni.quizkids.models.enums.Level;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "points",nullable = false)
    private Long point;
    @ManyToOne
    private Role role;
    @Enumerated(EnumType.STRING)
    private Level level;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Question> answeredQuestions;
}
