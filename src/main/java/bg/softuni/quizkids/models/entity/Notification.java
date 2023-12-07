package bg.softuni.quizkids.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseEntity{
    private String content;
    private LocalDateTime created;
    private boolean isRead;
    @ManyToOne
    private UserEntity user;
}
