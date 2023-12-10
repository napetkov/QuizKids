package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
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
import bg.softuni.quizkids.testUtils.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class QuestionServiceImplTest {
    @Autowired
    private QuestionService questionServiceToTest;
    @Autowired
    private UserRepository mockUserRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionRepository mockQuestionRepository;
    @Autowired
    private CategoryRepository mockCategoryRepository;
    @Autowired
    private AnswerRepository mockAnswerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TestData testData;

    @BeforeEach
    void setUp() {
        questionServiceToTest = new QuestionServiceImpl(
                mockUserRepository,
                answerService,
                mockQuestionRepository,
                mockCategoryRepository,
                mockAnswerRepository,
                modelMapper
        );
        testData.clearAllTestData();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddQuestionThrowUserNotFoundException() {
        testData.createUserWithRoleUser("admin", "topsecret", "pesho", "petrov");

        assertThrows(UsernameNotFoundException.class,
                () -> questionServiceToTest.addQuestion(new AddQuestionBindingModel(), "user"));
    }

    @Test
    void testAddQuestion() {
        testData.createUserWithRoleUser("author", "topsecret", "pesho", "petrov");
        UserEntity author = mockUserRepository.findByUsername("author").get();

        AddQuestionBindingModel addQuestionBindingModel = new AddQuestionBindingModel();
        addQuestionBindingModel.setContent("Test Test");
        addQuestionBindingModel.setCategory("MATH");
        addQuestionBindingModel.setAnswers(List.of(new AddAnswerBindingModel("test content"),
                new AddAnswerBindingModel("test content test")));

        assertThrows(UsernameNotFoundException.class,
                ()->questionServiceToTest.addQuestion(addQuestionBindingModel, "testUser"));

        questionServiceToTest.addQuestion(addQuestionBindingModel, "author");

        Optional<Question> optionalQuestion = mockQuestionRepository.findById(1L);
        assertEquals(1, mockQuestionRepository.count());
        assertTrue(optionalQuestion.isPresent());

        Question question = optionalQuestion.get();

        assertEquals(question.getContent(), addQuestionBindingModel.getContent());
        assertEquals(question.getCategory().getName().name(), addQuestionBindingModel.getCategory());
        assertEquals(question.getAnswers().size(), addQuestionBindingModel.getAnswers().size());
    }

    @Test
    void testFindQuestionByIdAndReturnDto() {
        createQuestions();

        Optional<QuestionDTO> optionalQuestionDTO = questionServiceToTest.findById(2L);

        assertTrue(optionalQuestionDTO.isPresent());
        assertEquals(optionalQuestionDTO.get().getId(), 2L);
        assertEquals(optionalQuestionDTO.get().getContent(), "Test2");

    }

    @Test
    void testDeleteQuestionById(){
        createQuestions();
        List<QuestionDTO> allQuestionsBefore = questionServiceToTest.getAllQuestions();

        assertEquals(3,allQuestionsBefore.size());
        questionServiceToTest.deleteQuestionById(2L);

        List<QuestionDTO> allQuestionsAfter = questionServiceToTest.getAllQuestions();
        assertEquals(2,allQuestionsAfter.size());
        assertTrue(questionServiceToTest.findById(2L).isEmpty());
    }

    private void createQuestions() {
        testData.createUserWithRoleUser("user1", "user1", "user1", "user1");
        testData.createUserWithRoleUser("user2", "user2", "user2", "user2");
        testData.createUserWithRoleUser("user3", "user3", "user3", "user3");

        Category category = mockCategoryRepository.findByName(CategoryName.MATH);


        Question question1 = new Question();
        question1.setId(1L);
        question1.setContent("Test1");
        question1.setCreatedOn(LocalDate.now());
        question1.setAuthor(mockUserRepository.findByUsername("user1").get());
        question1.setCategory(category);

        Question question2 = new Question();
        question2.setId(2L);
        question2.setContent("Test2");
        question2.setCreatedOn(LocalDate.now());
        question2.setAuthor(mockUserRepository.findByUsername("user2").get());
        question2.setCategory(category);

        Question question3 = new Question();
        question3.setId(3L);
        question3.setContent("Test3");
        question3.setCreatedOn(LocalDate.now());
        question3.setAuthor(mockUserRepository.findByUsername("user3").get());
        question3.setCategory(category);

        mockQuestionRepository.saveAll(List.of(question1, question2, question3));
    }

}