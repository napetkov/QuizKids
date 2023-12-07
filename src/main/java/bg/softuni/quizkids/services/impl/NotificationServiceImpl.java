package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.repository.NotificationRepository;
import bg.softuni.quizkids.services.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public long countByUserIdAndIsRead(long userId) {
        long l = notificationRepository.countByUser_IdAndAndIsReadIsFalse(userId);
        return l;
    }
}
