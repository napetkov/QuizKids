package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;

public interface UserService {
    void init();

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);
}
