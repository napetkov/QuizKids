package bg.softuni.quizkids.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "text", nullable = false, columnDefinition = "VARCHAR(5000)")
    private String text;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "is_read")
    private boolean isRead;

}
