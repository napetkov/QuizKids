package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    long countByUserIdAndIsRead(long userId);
    void clearOldNotification();

    List<NotificationDTO> getAllNotifications();

    void readNotification(Long id);
}
