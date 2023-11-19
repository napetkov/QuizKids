package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.admin.password}")
    public String adminPass;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
