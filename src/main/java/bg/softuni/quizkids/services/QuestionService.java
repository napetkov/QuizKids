package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.dto.QuestionDTO;
import bg.softuni.quizkids.models.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void addQuestion(AddQuestionBindingModel addQuestionBindingModel);

    List<QuestionDTO> getAllQuestions();

    Optional<QuestionDTO> findById(Long id);

    void deleteQuestionById(Long id);
}
