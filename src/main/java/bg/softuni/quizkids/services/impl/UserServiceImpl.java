package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.dto.UserProfileInfoDTO;
import bg.softuni.quizkids.models.entity.Notification;
import bg.softuni.quizkids.models.entity.Question;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import bg.softuni.quizkids.util.LoggedUserUtils;
import jakarta.validation.constraints.Null;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
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
    public List<UserEntityDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserEntityDTO.class))
                .toList();
    }

    @Override
    public List<UserEntityDTO> getAllUsersWithRole(UserRole role) {
        Role userRole = roleRepository.findByName(role);
        return userRepository.findAllByRole(userRole)
                .stream().map(user -> modelMapper.map(user, UserEntityDTO.class))
                .toList();
    }

    @Override
    public void updateUserRole(Long id, String newRoleName) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with id: " + id.toString() + "!");
        }
        UserEntity user = optionalUser.get();
        Role newRole = roleRepository.findByName(UserRole.valueOf(newRoleName));

        Role role = user.getRole();

        if (newRole != role) {
            user.setRole(newRole);
            userRepository.save(user);
        }
    }

    @Override
    public void scorePoint(Question question) {
        UserEntity user = getLoggedUser();

        user.getAnsweredQuestions().add(question);
        Long userPoint = user.getPoint();
        userPoint++;
        user.setPoint(userPoint);

        userLevelCheck(userPoint, user);

        userRepository.save(user);
    }

    @Override
    public UserProfileInfoDTO loggedUserProfileInfo() {
        UserEntity loggedUser = getLoggedUser();

        return mapUserEntityToUserProfileInfoDTO(loggedUser);
    }

    private UserProfileInfoDTO mapUserEntityToUserProfileInfoDTO(UserEntity loggedUser) {

        UserProfileInfoDTO loggedUserProfileInfoDto = modelMapper.map(loggedUser, UserProfileInfoDTO.class);
        Long usersPoint = loggedUserProfileInfoDto.getPoint();


        loggedUserProfileInfoDto.setPoint(loggedUser.getPoint());
        loggedUserProfileInfoDto.setCountOfAllUsers(userRepository.count());
        loggedUserProfileInfoDto.setCountAnsweredQuestions(loggedUser.getAnsweredQuestions().size());
        Long countOfUsersGreater = userRepository.countByPointIsGreaterThan(usersPoint);

        if (countOfUsersGreater == null) {
            countOfUsersGreater = 0L;
        }
        loggedUserProfileInfoDto.setPosition(countOfUsersGreater + 1);

        return loggedUserProfileInfoDto;
    }


    @Override
    public Set<String> getCategoriesOfNotAnsweredQuestions() {
        UserEntity user = getLoggedUser();

        Set<CategoryName> categoriesOfAnsweredQuestions = user.getAnsweredQuestions()
                .stream()
                .map(q -> q.getCategory().getName())
                .collect(Collectors.toSet());
        Set<CategoryName> categoriesOfNotAnsweredQuestions = new HashSet<>();

        for (CategoryName categoryName : CategoryName.values()) {
            if (!categoriesOfAnsweredQuestions.contains(categoryName)) {
                categoriesOfNotAnsweredQuestions.add(categoryName);
            }
        }

        return categoriesOfNotAnsweredQuestions.stream().map(Enum::name).collect(Collectors.toSet());
    }

    @Override
    public UserEntity getLoggedUser() {
        String username = LoggedUserUtils.getLoggedInUsername();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username + " was not found!");
        }

        return optionalUser.get();
    }


    private static void userLevelCheck(Long userPoint, UserEntity user) {
        Level currentLevel = user.getLevel();

        if (userPoint >= 50 && userPoint < 100) {
            user.setLevel(Level.INTERMEDIATE);
        } else if (userPoint >= 100 && userPoint < 150) {
            user.setLevel(Level.ADVANCED);
        } else if (userPoint >= 150) {
            user.setLevel(Level.EXPERT);
        }

        if (!currentLevel.equals(user.getLevel())) {
            StringBuilder notificationContent = new StringBuilder();
            notificationContent
                    .append(user.getUsername())
                    .append(", ")
                    .append("reached to ")
                    .append(user.getLevel().name())
                    .append(" after scoring ")
                    .append(user.getPoint())
                    .append("!!!");

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setRead(false);
            notification.setContent(notificationContent.toString());
            notification.setCreated(LocalDateTime.now());
        }
    }


//    @Override
//    public void initBlacklisted() {
//        if (roleRepository.count() == 3) {
//            Role roleBlackListed = new Role();
//            roleBlackListed.setName(UserRole.BLACKLISTED);
//            roleRepository.save(roleBlackListed);
//        }
//    }

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

            Role roleBlackListed = new Role();
            roleUser.setName(UserRole.BLACKLISTED);
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
            admin.setPoint(1L);

            userRepository.save(admin);

        }
    }


}
