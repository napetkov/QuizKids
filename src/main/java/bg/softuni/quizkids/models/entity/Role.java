package bg.softuni.quizkids.models.entity;

import bg.softuni.quizkids.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserRole name;
}
