package bg.softuni.quizkids.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTestIt {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    void testDisplayAdminPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}