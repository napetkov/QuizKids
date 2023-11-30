package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;

public interface PlayService {
    QuestionAndAnswerDTO getRandomQuestionFromAll();
}
