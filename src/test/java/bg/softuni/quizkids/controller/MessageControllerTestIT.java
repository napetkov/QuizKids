package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;
import bg.softuni.quizkids.models.entity.MessageEntity;
import bg.softuni.quizkids.services.MessageService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestData testData;
    @BeforeEach
    void setUp() {
        testData.clearAllTestData();
    }

    @AfterEach
    void tearDown() {
        testData.clearAllTestData();
    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testAllNotifications() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/message"))
                .andExpect(status().isOk())
                .andExpect(view().name("message"));
    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testMakeNotificationRead() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/message/1")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/message"));

    }

}