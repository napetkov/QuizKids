package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
//    @Query(value = "SELECT count(n.id) from Notification as n where n.user.id = :userId and n.isRead is true")
    long countByUser_IdAndAndIsReadIsFalse(long userId);

    void deleteAllByCreatedBefore(LocalDateTime localDateTime);
}
