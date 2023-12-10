package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.models.dto.QuestionAndAnswerDTO;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.testUtils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PlayServiceImplTest {
    @Autowired
    private PlayServiceImpl playServiceToTest;
    @Autowired
    private  QuestionRepository questionRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  UserService userService;
    private QuestionService questionService;
    @Autowired
    private  ModelMapper modelMapper;
    private TestData testData = new TestData();
    @BeforeEach
    void setUp() {
        playServiceToTest = new PlayServiceImpl(
            questionRepository,
            userRepository,
            userService,
            modelMapper
        );
    }

    @Test
    void testFindQuestionByIdToQuestionAndAnswerDTO(){
        testData.createUserWithRoleUser("author", "topsecret", "pesho", "petrov");
        UserEntity author = userRepository.findByUsername("author").get();

        AddQuestionBindingModel addQuestionBindingModel = new AddQuestionBindingModel();
        addQuestionBindingModel.setContent("Test Test");
        addQuestionBindingModel.setCategory("MATH");
        addQuestionBindingModel.setAnswers(List.of(new AddAnswerBindingModel("test content"),
                new AddAnswerBindingModel("test content test")));


        questionService.addQuestion(addQuestionBindingModel, "author");

        QuestionAndAnswerDTO questionAndAnswerDTO = playServiceToTest
                .findQuestionByIdToQuestionAndAnswerDTO(1L);

        assertEquals(addQuestionBindingModel.getContent(),questionAndAnswerDTO.getContent());
        assertEquals(addQuestionBindingModel.getAnswers().size(),questionAndAnswerDTO.getAnswers().size());
        assertEquals(addQuestionBindingModel.getCategory(),questionAndAnswerDTO.getCategoryName());

    }
}