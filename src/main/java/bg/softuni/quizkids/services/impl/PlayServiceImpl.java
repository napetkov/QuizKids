package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.dto.AnswerDTO;
import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.services.PlayService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayServiceImpl implements PlayService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public PlayServiceImpl(QuestionRepository questionRepository,
                           AnswerRepository answerRepository,
                           ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionAndAnswerDTO getRandomQuestionFromAll() {
        Question randomQuestion = questionRepository.findRandomQuestion();
        QuestionAndAnswerDTO questionAndAnswerDTO = modelMapper.map(randomQuestion, QuestionAndAnswerDTO.class);

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
