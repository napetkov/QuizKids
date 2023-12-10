package bg.softuni.quizkids.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private String content;
    private LocalDateTime created;
    private boolean isRead;
    private String ownerUsername;
}
