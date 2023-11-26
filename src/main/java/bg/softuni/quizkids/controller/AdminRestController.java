package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.dto.QuestionDTO;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionDTO> deleteQuestionById(@PathVariable("id") Long id,@RequestBody String newRole){

    userService.updateUserRole(id,newRole);

        return ResponseEntity
                .noContent()
                .build();
    }


}
