package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.services.AnswerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void addAnswer(AddAnswerBindingModel addAnswerBindingModel) {

    }

    @Override
    public List<Answer> addAllAnswersWhenCreateQuestion(List<AddAnswerBindingModel> answersBinding, UserEntity author) {
        List<Answer> answers = new ArrayList<>();

        for (int i = 0; i < answersBinding.size(); i++) {
            Answer answer = new Answer();

            if(i==0){
                answer.setCorrect(true);
            }

            answer.setAuthor(author);
            answer.setCreatedOn(LocalDate.now());
            answer.setContent(answersBinding.get(i).getContent());
            answers.add(answer);
        }
        answerRepository.saveAll(answers);
    return answers;
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

}
