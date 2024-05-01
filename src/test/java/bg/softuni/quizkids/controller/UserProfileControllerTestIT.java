package bg.softuni.quizkids.controller;

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
class UserProfileControllerTestIT {
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
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetUserProfile() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testEditProfile() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-profile"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testEditUserProfile() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/users/profile/edit")
                        .param("firstName", "Pesho")
                        .param("lastName", "Petrov")
                        .param("email", "pesho@example.com")
                        .param("age", "13")
                        .param("city", "Ruse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testEditUserProfileNoValidInput() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/users/profile/edit")
                        .param("firstName", " ")
                        .param("lastName", " ")
                        .param("email", "pesho-example.com")
                        .param("city", "Ruse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile/edit"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testChangePasswordGet() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/edit/changePassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("change-password"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testChangePasswordPostNoValidInput() throws Exception {

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/users/profile/edit/changePassword")
                        .param("oldPassword", " ")
                        .param("password", " ")
                        .param("confirmPassword", " ")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile/edit/changePassword"));
    }

}