package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from Question as q order by RAND() limit 1")
    Question findRandomQuestion();
    @Query("select q from Question as q where q not in :answeredQuestions  order by RAND() limit 1")
    Question findRandomQuestionNotInAnsweredQuestions(Set<Question> answeredQuestions);
    @Query("select q from Question as q where q.category.name = :categoryName order by RAND() limit 1")
    Question findRandomByCategoryName(CategoryName categoryName);
    @Query("select q from Question as q where q.category.name = :categoryName and q not in :answeredQuestions order by RAND() limit 1")
    Question findRandomByCategoryNameIsNotInAnsweredQuestions(CategoryName categoryName, Set<Question> answeredQuestions);
}
