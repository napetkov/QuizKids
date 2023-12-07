package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.models.enums.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    void init();

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    List<UserEntityDTO> getAllUsers();

    void updateUserRole(Long id, String newRole);

    List<UserEntityDTO> getAllUsersWithRole(UserRole role);

    void scorePoint(Question question);

    Set<String> getCategoriesOfNotAnsweredQuestions();

    UserEntity getLoggedUser();

//    void initBlacklisted();

}
