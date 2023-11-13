package bg.softuni.quizkids.models.entity;

import bg.softuni.quizkids.models.enums.CategoryName;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryName name;

    @Column(name = "description")
    private String description;
}
