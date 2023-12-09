package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.testUtils.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminRestControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestData testData;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void testDisplayAllUsers() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleModerator(
                "moderator",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(2, users.length);
        Assertions.assertEquals("admin", users[0].getUsername());
        Assertions.assertEquals(UserRole.ADMIN, users[0].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[0].getLevel());

        Assertions.assertEquals("moderator", users[1].getUsername());
        Assertions.assertEquals(UserRole.MODERATOR, users[1].getRole().getName());
        Assertions.assertEquals(Level.ADVANCED, users[1].getLevel());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayBlacklistedUsers() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleBlacklisted(
                "blacklisted",
                "topsecret",
                "Niko",
                "Pet");
        testData.createUserWithRoleBlacklisted(
                "blacklisted2",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/blacklisted")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(2, users.length);
        Assertions.assertEquals("blacklisted", users[0].getUsername());
        Assertions.assertEquals(UserRole.BLACKLISTED, users[0].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[0].getLevel());

        Assertions.assertEquals("blacklisted2", users[1].getUsername());
        Assertions.assertEquals(UserRole.BLACKLISTED, users[1].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[1].getLevel());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayModerators() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleModerator(
                "moderator",
                "topsecret",
                "Niko",
                "Pet");
        testData.createUserWithRoleModerator(
                "moderator2",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/moderators")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(2, users.length);
        Assertions.assertEquals("moderator", users[0].getUsername());
        Assertions.assertEquals(UserRole.MODERATOR, users[0].getRole().getName());
        Assertions.assertEquals(Level.ADVANCED, users[0].getLevel());

        Assertions.assertEquals("moderator2", users[1].getUsername());
        Assertions.assertEquals(UserRole.MODERATOR, users[1].getRole().getName());
        Assertions.assertEquals(Level.ADVANCED, users[1].getLevel());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayAllWithRoleUser() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleUser(
                "user",
                "topsecret",
                "Niko",
                "Pet");
        testData.createUserWithRoleUser(
                "user2",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/users")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(2, users.length);
        Assertions.assertEquals("user", users[0].getUsername());
        Assertions.assertEquals(UserRole.USER, users[0].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[0].getLevel());

        Assertions.assertEquals("user2", users[1].getUsername());
        Assertions.assertEquals(UserRole.USER, users[1].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[1].getLevel());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayAdmins() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleAdmin(
                "admin1",
                "topsecret",
                "Niko",
                "Pet");
        testData.createUserWithRoleAdmin(
                "admin2",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/admins")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(3, users.length);
        Assertions.assertEquals("admin1", users[1].getUsername());
        Assertions.assertEquals(UserRole.ADMIN, users[1].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[1].getLevel());

        Assertions.assertEquals("admin2", users[2].getUsername());
        Assertions.assertEquals(UserRole.ADMIN, users[2].getRole().getName());
        Assertions.assertEquals(Level.BEGINNER, users[2].getLevel());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testChangeUserRole() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );
        testData.createUserWithRoleModerator(
                "moderator",
                "topsecret",
                "Niko",
                "Pet");

        String responseJson = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/")
                                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserEntity[] users = objectMapper.readValue(responseJson, UserEntity[].class);

        Assertions.assertEquals(2, users.length);

        Long userId = users[1].getId();

        userService.updateUserRole(userId, "USER");
        UserEntity userAfter = userRepository.findById(userId).get();

        Assertions.assertEquals(UserRole.USER, userAfter.getRole().getName());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testChangeUserRoleReturnStatus() throws Exception {

        testData.clearAllTestData();

        testData.createUserWithRoleAdmin(
                "admin",
                "topsecret",
                "Pesho",
                "Petrov"
        );

        UserEntity user = userRepository.findByUsername("admin").get();
        Long userId = user.getId();
        String requestBody = "USER";

        mockMvc.perform(
                        patch("/api/users/{userId}", userId)
                                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isNoContent());
    }
}