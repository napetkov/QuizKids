package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.QuestionNotFoundException;
import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.dto.AnswerDTO;
import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.PlayService;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayServiceImpl implements PlayService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PlayServiceImpl(QuestionRepository questionRepository,
                           AnswerRepository answerRepository,
                           UserRepository userRepository,
                           UserService userService,
                           ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionAndAnswerDTO getRandomQuestionFromAll() {
        String username = LoggedUserUtils.getLoggedInUsername();

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotUniqueException(username);
        }
        UserEntity user = userOptional.get();

        List<Question> answeredQuestions = user.getAnsweredQuestions();
        Question randomQuestion = questionRepository.findRandomQuestion(answeredQuestions);

        return createQuestionAndAnswerDTO(randomQuestion);
    }

    @Override
    public QuestionAndAnswerDTO findQuestionByIdToQuestionAndAnswerDTO(long questionId) {
        Question question = findQuestionById(questionId);

        return createQuestionAndAnswerDTO(question);
    }

    private Question findQuestionById(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if(optionalQuestion.isEmpty()){
            throw new QuestionNotFoundException("Question with id: " + questionId + "was not found!");
        }

        return optionalQuestion.get();
    }

    @Override
    public void correctlyAnsweringOfQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        userService.scorePoint(question);
    }

    private QuestionAndAnswerDTO createQuestionAndAnswerDTO(Question question) {
        QuestionAndAnswerDTO questionAndAnswerDTO = modelMapper.map(question, QuestionAndAnswerDTO.class);

        List<AnswerDTO> answers = questionAndAnswerDTO.getAnswers();
        List<AnswerDTO> answersSubSet = getAnswersSubSet(answers);

        questionAndAnswerDTO.setAnswers(answersSubSet);

        return questionAndAnswerDTO;
    }

    public List<AnswerDTO> getAnswersSubSet(List<AnswerDTO> answers) {
        List<AnswerDTO> answerSubList = new ArrayList<>();
        answerSubList.add(answers.get(0));
        answers.remove(0);

        Collections.shuffle(answers);
        for (int i = 0; i < 3; i++) {
            answerSubList.add(answers.get(i));
        }

        Collections.shuffle(answerSubList);

        return answerSubList;
    }


}
