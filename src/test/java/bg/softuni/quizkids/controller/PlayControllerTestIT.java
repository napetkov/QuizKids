package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.models.binding.AddQuestionBindingModel;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.services.QuestionService;
import bg.softuni.quizkids.testUtils.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PlayControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestData testData;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;

//    @BeforeEach
//    void setUp() {
//        testData.clearAllTestData();
//    }
//
//    @AfterEach
//    void tearDown() {
//        testData.clearAllTestData();
//    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testPlayQuestionById() throws Exception {
//        testData.createUserWithRoleAdmin(
//                "admin",
//                "topsecret",
//                "Pesho",
//                "Petrov"
//        );

        AddQuestionBindingModel addQuestionBindingModel = createQuestionFromBindingModel();

        questionService.addQuestion(addQuestionBindingModel,"admin");

        mockMvc.perform(MockMvcRequestBuilders.get("/play/questions/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("play"));

    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testPlayByCategoryName() throws Exception {


        AddQuestionBindingModel addQuestionBindingModel = createQuestionFromBindingModel();

        questionService.addQuestion(addQuestionBindingModel,"admin");

        mockMvc.perform(MockMvcRequestBuilders.get("/play/MATH"))
                .andExpect(status().isOk())
                .andExpect(view().name("play"));

    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testPlayAll() throws Exception {


        AddQuestionBindingModel addQuestionBindingModel = createQuestionFromBindingModel();

        questionService.addQuestion(addQuestionBindingModel,"admin");

        mockMvc.perform(MockMvcRequestBuilders.get("/play/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("play"));

    }

    private static AddQuestionBindingModel createQuestionFromBindingModel() {
        AddQuestionBindingModel addQuestionBindingModel = new AddQuestionBindingModel();
        addQuestionBindingModel.setContent("Test Content");
        addQuestionBindingModel.setCategory("MATH");
        addQuestionBindingModel.setAnswers(List.of(
                new AddAnswerBindingModel("Correct answer"),
                new AddAnswerBindingModel("Incorrect answer"),
                new AddAnswerBindingModel("Incorrect answer"),
                new AddAnswerBindingModel("Incorrect answer"),
                new AddAnswerBindingModel("Incorrect answer"),
                new AddAnswerBindingModel("Incorrect answer")
        ));
        return addQuestionBindingModel;
    }
}