package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
    Optional<Long> countByIsReadFalse();
}
