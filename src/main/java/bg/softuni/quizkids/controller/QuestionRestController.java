package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.dto.QuestionsDTO;
import bg.softuni.quizkids.services.QuestionService;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {
    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionsDTO>> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
}
