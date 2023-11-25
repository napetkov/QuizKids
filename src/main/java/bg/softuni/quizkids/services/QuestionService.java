package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.dto.QuestionsDTO;

import java.util.List;

public interface QuestionService {
    void addQuestion(AddQuestionBindingModel addQuestionBindingModel);

    List<QuestionsDTO> getAllQuestions();
}
