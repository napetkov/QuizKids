package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.QuestionNotFoundException;
import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.dto.QuestionDTO;
import bg.softuni.quizkids.models.entity.Answer;
import bg.softuni.quizkids.models.entity.Category;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.repository.CategoryRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.AnswerService;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final AnswerService answerService;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public QuestionServiceImpl(UserRepository userRepository,
                               AnswerService answerService,
                               QuestionRepository questionRepository,
                               CategoryRepository categoryRepository,
                               AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
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

        Question question = createQuestion(addQuestionBindingModel, author, createdOn, category);

    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream().map(QuestionServiceImpl::mapQuestionEntityToDTO).toList();
    }

    @Override
    public Optional<QuestionDTO> findById(Long id) {
        return questionRepository.findById(id).map(QuestionServiceImpl::mapQuestionEntityToDTO);
    }

    @Override
    public void deleteQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isEmpty()){
            throw new QuestionNotFoundException("Question with id: " + id + " was not found");
        }
        Question question = optionalQuestion.get();
        List<Answer> answers = question.getAnswers();

        answerRepository.deleteAll(answers);

        questionRepository.deleteById(id);
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

    private static QuestionDTO mapQuestionEntityToDTO(Question questions) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questions.getId());
        questionDTO.setContent(questions.getContent());
        questionDTO.setCategoryName(questions.getCategory().getName().name());
        questionDTO.setAuthorUsername(questions.getAuthor().getUsername());
        questionDTO.setCreatedOn(questions.getCreatedOn());

        return questionDTO;
    }

}
