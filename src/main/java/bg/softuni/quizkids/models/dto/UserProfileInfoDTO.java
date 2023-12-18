package bg.softuni.quizkids.models.dto;

import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class UserProfileInfoDTO {
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String city;
    private Long point;
    private Level level;
    private int countAnsweredQuestions;
    private long position;
    private long countOfAllUsers;

}
