package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.dto.NotificationDTO;
import bg.softuni.quizkids.models.entity.Notification;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.NotificationRepository;
import bg.softuni.quizkids.services.NotificationService;
import bg.softuni.quizkids.services.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    @Override
    public long countByUserIdAndIsRead(long userId) {
        long count = notificationRepository.countByUser_IdAndAndIsReadIsFalse(userId);

        return count;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void clearOldNotification() {
        notificationRepository.deleteAllByCreatedBefore(LocalDateTime.now().minusDays(30));
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        UserEntity user = userService.getLoggedUser();
        Long userId = user.getId();

        List<Notification> notifications = notificationRepository.findAllByUserId(userId);

        return notifications
                .stream()
                .map(NotificationServiceImpl::mapToNotificationDTO)
                .toList();
    }

    @Override
    public void readNotification(Long id) {
        Optional<Notification> optionalNotif = notificationRepository.findById(id);

        if(optionalNotif.isEmpty()){
            throw new IllegalArgumentException(
                    "Notification with id:" + id + "was not found!");
        }

        Notification notification = optionalNotif.get();
        notification.setRead(true);

        notificationRepository.save(notification);
    }

    public static NotificationDTO mapToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setOwnerUsername(notification.getUser().getUsername());
        notificationDTO.setContent(notification.getContent());
        notificationDTO.setCreated(notification.getCreated());
        notificationDTO.setRead(notification.isRead());
        notificationDTO.setId(notification.getId());

        return notificationDTO;
    }
}
