package bg.softuni.quizkids.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/question/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-question"));
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testAddPostValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/question/add")
                        .param("content", "Test Content")
                        .param("category","MATH")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/question/all"));
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testAddPostFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/question/add")
                        .param("content", "T")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/question/add"));
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    public void testAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/question/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("question-all"));
    }
}