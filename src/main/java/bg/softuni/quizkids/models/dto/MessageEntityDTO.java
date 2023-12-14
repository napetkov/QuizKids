package bg.softuni.quizkids.models.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class MessageEntityDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String text;
    private LocalDateTime createdOn;
    private boolean isRead;

}
