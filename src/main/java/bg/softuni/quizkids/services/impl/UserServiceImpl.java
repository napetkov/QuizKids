package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Value("${app.admin.password}")
    public String adminPass;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        String username = userRegisterBindingModel.getUsername();

        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);


        if (userOptional.isPresent()) {
            throw new UserNotUniqueException(username);
        }

        UserEntity userEntity = modelMapper.map(userRegisterBindingModel, UserEntity.class);

        userRepository.save(userEntity);
    }

    @Override
    public void init() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            List<Role> baseRoles = new ArrayList<>();

            Role roleAdmin = new Role();
            roleAdmin.setName(UserRole.ADMIN);
            baseRoles.add(roleAdmin);

            Role roleModerator = new Role();
            roleModerator.setName(UserRole.MODERATOR);
            baseRoles.add(roleModerator);

            Role roleUser = new Role();
            roleUser.setName(UserRole.USER);
            baseRoles.add(roleUser);

            roleRepository.saveAll(baseRoles);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode(adminPass));
            admin.setFirstName("Nikolay");
            admin.setLastName("Petkov");
            admin.setAge(38);
            admin.setLevel(Level.EXPERT);
            admin.setCity("Sofia");
            admin.setRole(roleAdmin);

            userRepository.save(admin);

        }
    }


}
