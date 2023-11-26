package bg.softuni.quizkids.models.dto;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.Level;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEntityDTO {
    private long id;
    private String username;
    private String email;
    private Role role;
    private Level level;
}
