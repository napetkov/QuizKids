package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.dto.UserEntityDTO;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUsers(){
        return ResponseEntity.ok();
    }

}
