package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from Question as q where q not in :answeredQuestions order by RAND() limit 1")
    Question findRandomQuestionNotInAnsweredQuestions(List<Question> answeredQuestions);
    @Query("select q from Question as q where q.category.name = :categoryName and q not in :answeredQuestions order by RAND() limit 1")
    Question findAllByCategoryName(CategoryName categoryName, List<Question> answeredQuestions);
}
