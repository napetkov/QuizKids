package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.entity.Notification;
import bg.softuni.quizkids.repository.NotificationRepository;
import bg.softuni.quizkids.services.NotificationService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TestData testData;

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
    public void testAllNotifications() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/notification"))
                .andExpect(status().isOk())
                .andExpect(view().name("notification"));
    }
    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testMakeNotificationRead() throws Exception {



        notificationRepository.save(new Notification());

        mockMvc.perform(MockMvcRequestBuilders.post("/notification/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notification"));
    }
}