package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;
import bg.softuni.quizkids.models.enums.CategoryName;

public interface PlayService {
    QuestionAndAnswerDTO getRandomQuestionFromAll();

    QuestionAndAnswerDTO findQuestionByIdToQuestionAndAnswerDTO(long questionId);

    void correctlyAnsweringOfQuestion(Long questionId);

    QuestionAndAnswerDTO getRandomQuestionFromCategory(CategoryName categoryName);
}
