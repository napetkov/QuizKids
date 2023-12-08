package bg.softuni.quizkids.services;

public interface NotificationService {
    long countByUserIdAndIsRead(long userId);
    void clearOldNotification();
}
