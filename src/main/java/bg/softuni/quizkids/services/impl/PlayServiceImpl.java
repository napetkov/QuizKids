package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.QuestionNotFoundException;
import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.dto.AnswerDTO;
import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;
import bg.softuni.quizkids.models.entity.BaseEntity;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.PlayService;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayServiceImpl implements PlayService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PlayServiceImpl(QuestionRepository questionRepository,
                           UserRepository userRepository,
                           UserService userService,
                           ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionAndAnswerDTO getRandomQuestionFromCategory(CategoryName categoryName) {
        UserEntity user = userService.getLoggedUser();

        Set<Question> answeredQuestions = user.getAnsweredQuestions();
        Question questionByCategory = new Question();

        if (answeredQuestions.isEmpty()) {
            questionByCategory = questionRepository.findRandomByCategoryName(categoryName);
        } else {
            questionByCategory = questionRepository.findRandomByCategoryNameIsNotInAnsweredQuestions(categoryName, answeredQuestions);
        }
        return createQuestionAndAnswerDTO(questionByCategory);
    }

    @Override
    public QuestionAndAnswerDTO getRandomQuestionFromAll() {

        UserEntity user = getLoggedUser();
        Set<Question> answeredQuestions = user.getAnsweredQuestions();
        Question randomQuestion = new Question();

        if (answeredQuestions.isEmpty()) {
            randomQuestion = questionRepository.findRandomQuestion();
        } else {
            randomQuestion = questionRepository.findRandomQuestionNotInAnsweredQuestions(answeredQuestions);
        }

        return createQuestionAndAnswerDTO(randomQuestion);
    }


    @Override
    public QuestionAndAnswerDTO findQuestionByIdToQuestionAndAnswerDTO(long questionId) {

        Question question = findQuestionByIdNotInAnsweredQuestions(questionId);
        return createQuestionAndAnswerDTO(question);

    }

    private Question findQuestionByIdNotInAnsweredQuestions(long questionId) {
        //TODO: return something when already answer of all questions from given category or else
        //size of answered question will == to size of all question
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        UserEntity user = getLoggedUser();
        Set<Long> answeredQuestions = user.getAnsweredQuestions()
                .stream().map(BaseEntity::getId)
                .collect(Collectors.toSet());

        if (optionalQuestion.isEmpty() || answeredQuestions.contains(optionalQuestion.get().getId())) {
            throw new QuestionNotFoundException("Question with id: " + questionId + " was not found or is already answered!");
        }

        return optionalQuestion.get();
    }

    private UserEntity getLoggedUser() {
        String username = LoggedUserUtils.getLoggedInUsername();

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotUniqueException(username);
        }
        return userOptional.get();
    }

    @Override
    public void correctlyAnsweringOfQuestion(Long questionId) {
        Question question = findQuestionByIdNotInAnsweredQuestions(questionId);
        userService.scorePoint(question);
    }

    private QuestionAndAnswerDTO createQuestionAndAnswerDTO(Question question) {
        if (question == null){
            throw new QuestionNotFoundException("This question was not found or already was answered correctly!");
        }

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
