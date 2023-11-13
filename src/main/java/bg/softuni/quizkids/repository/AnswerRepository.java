package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
