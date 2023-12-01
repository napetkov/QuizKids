package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from Question as q where q not in :answeredQuestions order by RAND() limit 1")
    Question findRandomQuestion(List<Question> answeredQuestions);
}
