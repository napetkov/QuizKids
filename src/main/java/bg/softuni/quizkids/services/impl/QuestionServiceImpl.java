package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.AnswerService;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final UserRepository userRepository;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(UserRepository userRepository, AnswerService answerService, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
    }

    @Override
    public void addQuestion(AddQuestionBindingModel addQuestionBindingModel) {
        String loggedInUsername = LoggedUserUtils.getLoggedInUsername();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(loggedInUsername);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(loggedInUsername);
        }

        UserEntity author = optionalUser.get();
        LocalDate createdOn = LocalDate.now();

        Question question = map(addQuestionBindingModel, author, createdOn);

        questionRepository.save(question);
//        TODO: Test implementation and implement functionality in html
    }

    private Question map(AddQuestionBindingModel addQuestionBindingModel, UserEntity author, LocalDate createdOn) {
        Question question = new Question();

        List<Answer> answers = answerService.addAllAnswersWhenCreateQuestion(addQuestionBindingModel.getAnswers(), author);

        question.setAuthor(author);
        question.setContent(addQuestionBindingModel.getContent());
        question.setCreatedOn(createdOn);
        question.setAnswers(answers);

        questionRepository.save(question);

        answers.forEach(answer -> {
            answer.setQuestion(question);
            answerService.saveAnswer(answer);
        });

        return question;
    }

    private Answer mapAnswer(AddAnswerBindingModel addAnswerBindingModel, UserEntity author, LocalDate createdOn) {
        Answer answer = new Answer();
        answer.setAuthor(author);
        answer.setCreatedOn(createdOn);
        answer.setContent(addAnswerBindingModel.getContent());

        return answer;
    }
}
