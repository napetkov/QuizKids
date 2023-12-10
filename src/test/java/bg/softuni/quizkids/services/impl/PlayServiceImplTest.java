package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.QuestionNotFoundException;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PlayServiceImplTest {
    @Autowired
    private PlayServiceImpl playServiceToTest;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TestData testData;

    @BeforeEach
    void setUp() {
        playServiceToTest = new PlayServiceImpl(
                questionRepository,
                userRepository,
                userService,
                modelMapper
        );
        testData.clearAllTestData();
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    void testFindQuestionByIdThrowQuestionNotFound() {

        testData.createUserWithRoleUser("admin", "topsecret", "pesho", "petrov");

        UserEntity author = userRepository.findByUsername("admin").get();

        AddQuestionBindingModel addQuestionBindingModel = getAddQuestionBindingModel();

        questionService.addQuestion(addQuestionBindingModel, "admin");

        assertThrows(QuestionNotFoundException.class,
                () -> playServiceToTest.findQuestionByIdToQuestionAndAnswerDTO(3L));
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    void testFindQuestionByIdToQuestionAndAnswerDTO() {

        testData.createUserWithRoleUser("admin", "topsecret", "pesho", "petrov");

        UserEntity author = userRepository.findByUsername("admin").get();

        AddQuestionBindingModel addQuestionBindingModel = getAddQuestionBindingModel();


        questionService.addQuestion(addQuestionBindingModel, "admin");

        QuestionAndAnswerDTO questionAndAnswerDTO = playServiceToTest
                .findQuestionByIdToQuestionAndAnswerDTO(1L);

        assertEquals(addQuestionBindingModel.getContent(), questionAndAnswerDTO.getContent());
        assertEquals(addQuestionBindingModel.getAnswers().size(), questionAndAnswerDTO.getAnswers().size());
        assertEquals(addQuestionBindingModel.getCategory(), questionAndAnswerDTO.getCategoryName());

    }

    private static AddQuestionBindingModel getAddQuestionBindingModel() {
        AddQuestionBindingModel addQuestionBindingModel = new AddQuestionBindingModel();
        addQuestionBindingModel.setContent("Test Test");
        addQuestionBindingModel.setCategory("MATH");
        addQuestionBindingModel.setAnswers(List.of(
                new AddAnswerBindingModel("test content 1"),
                new AddAnswerBindingModel("test content 2"),
                new AddAnswerBindingModel("test content 3"),
                new AddAnswerBindingModel("test content 4")
                ));
        return addQuestionBindingModel;
    }
}