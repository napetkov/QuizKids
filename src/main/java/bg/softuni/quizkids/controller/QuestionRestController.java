package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.dto.QuestionDTO;
import bg.softuni.quizkids.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {
    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    ResponseEntity<QuestionDTO> findByID(@PathVariable("id") Long id){
        Optional<QuestionDTO> optionalQuestion = questionService.findById(id);

        return optionalQuestion
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionDTO> deleteQuestionById(@PathVariable("id") Long id){
        return ResponseEntity
                .noContent()
                .build();
    }



}
