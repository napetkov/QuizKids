package bg.softuni.quizkids.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEditInfoDTO {
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String city;
}
