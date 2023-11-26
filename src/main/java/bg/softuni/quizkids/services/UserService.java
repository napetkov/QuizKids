package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.dto.UserEntityDTO;

import java.util.List;

public interface UserService {
    void init();

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    List<UserEntityDTO> getAllUsers();

    void updateUserRole(Long id, String newRole);

//    void initBlacklisted();

}
