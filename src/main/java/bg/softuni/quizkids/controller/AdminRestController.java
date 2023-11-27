package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.dto.QuestionDTO;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.enums.UserRole;
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

    @GetMapping("/")
    public ResponseEntity<List<UserEntityDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/blacklisted")
    public ResponseEntity<List<UserEntityDTO>> getAllUsersWithRoleBlacklisted(){
        return ResponseEntity.ok(userService.getAllUsersWithRole(UserRole.BLACKLISTED));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntityDTO>> getAllUsersWithRoleUser(){
        return ResponseEntity.ok(userService.getAllUsersWithRole(UserRole.USER));
    }

    @GetMapping("/moderators")
    public ResponseEntity<List<UserEntityDTO>> getAllUsersWithRoleModerator(){
        return ResponseEntity.ok(userService.getAllUsersWithRole(UserRole.MODERATOR));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserEntityDTO>> getAllUsersWithRoleAdmin(){
        return ResponseEntity.ok(userService.getAllUsersWithRole(UserRole.ADMIN));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionDTO> deleteQuestionById(@PathVariable("id") Long id,@RequestBody String newRole){

    userService.updateUserRole(id,newRole);

        return ResponseEntity
                .noContent()
                .build();
    }


}
