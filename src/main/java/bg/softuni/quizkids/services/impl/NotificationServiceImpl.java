package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.repository.NotificationRepository;
import bg.softuni.quizkids.services.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public long countByUserIdAndIsRead(long userId) {
        return notificationRepository.countByUser_IdAndAndIsReadIsFalse(userId);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void clearOldNotification() {
        notificationRepository.deleteAllByCreatedBefore(LocalDateTime.now().minusMinutes(30));
    }
}
