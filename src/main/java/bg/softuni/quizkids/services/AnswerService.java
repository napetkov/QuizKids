package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.AnswerRepository;

import java.util.List;

public interface AnswerService {
    void addAnswer(AddAnswerBindingModel addAnswerBindingModel);

    List<Answer> addAllAnswersWhenCreateQuestion(List<AddAnswerBindingModel> answers, UserEntity author);

    void saveAnswer(Answer answer);
}
