package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.Category;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.repository.CategoryRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.AnswerService;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final UserRepository userRepository;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    public QuestionServiceImpl(UserRepository userRepository, AnswerService answerService, QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
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
        Category category = categoryRepository.findByName(CategoryName.valueOf(addQuestionBindingModel.getCategory()));

        Question question = createQuestion(addQuestionBindingModel, author, createdOn,category);

    }

    private Question createQuestion(AddQuestionBindingModel addQuestionBindingModel,
                                    UserEntity author,
                                    LocalDate createdOn,
                                    Category category) {
        Question question = new Question();

        List<Answer> answers = answerService.addAllAnswersWhenCreateQuestion(addQuestionBindingModel.getAnswers(), author);

        question.setAuthor(author);
        question.setContent(addQuestionBindingModel.getContent());
        question.setCreatedOn(createdOn);
        question.setAnswers(answers);
        question.setCategory(category);

        questionRepository.save(question);

        answers.forEach(answer -> {
            answer.setQuestion(question);
            answerService.saveAnswer(answer);
        });

        return question;
    }

}
