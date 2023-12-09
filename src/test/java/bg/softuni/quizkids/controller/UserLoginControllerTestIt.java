package bg.softuni.quizkids.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTestIt {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "username",password = "topsecret",roles = "ADMIN")
    void testLogin() throws Exception {
        mockMvc.perform(
                post("/login")
                        .param("username","username")
                        .param("password","topsecret")
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection());
    }

}