package bg.softuni.quizkids.testUtils;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.AnswerRepository;
import bg.softuni.quizkids.repository.QuestionRepository;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public void createUserWithRoleUser(
            String username,
            String password,
            String firstName,
            String lastName
    ) {
        if(roleRepository.count()==0){
            createRolesInit();
        }

        Role usersWithRoleUser = roleRepository.findByName(UserRole.USER);
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(usersWithRoleUser);
        user.setLevel(Level.BEGINNER);
        user.setPoint(20L);

        userRepository.save(user);
    }

    public void createUserWithRoleModerator(
            String username,
            String password,
            String firstName,
            String lastName
    ) {
        if(roleRepository.count()==0){
            createRolesInit();
        }

        Role moderator = roleRepository.findByName(UserRole.MODERATOR);
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(moderator);
        user.setLevel(Level.ADVANCED);
        user.setPoint(20L);

        userRepository.save(user);
    }

    public void createUserWithRoleBlacklisted(
            String username,
            String password,
            String firstName,
            String lastName
    ) {
        if(roleRepository.count()==0){
            createRolesInit();
        }

        Role blacklisted = roleRepository.findByName(UserRole.BLACKLISTED);
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(blacklisted);
        user.setLevel(Level.BEGINNER);
        user.setPoint(20L);

        userRepository.save(user);
    }
    public void createUserWithRoleAdmin(
            String username,
            String password,
            String firstName,
            String lastName
            ){
        if(roleRepository.count()==0){
            createRolesInit();
        }

        Role admin = roleRepository.findByName(UserRole.ADMIN);
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(admin);
        user.setLevel(Level.BEGINNER);
        user.setPoint(20L);

        userRepository.save(user);
    }

    private void createRolesInit() {

        Role roleEntity = new Role();
        Role roleEntity1 = new Role();
        Role roleEntity2 = new Role();
        Role roleEntity3 = new Role();
        roleEntity.setName(UserRole.ADMIN);
        roleEntity1.setName(UserRole.MODERATOR);
        roleEntity2.setName(UserRole.USER);
        roleEntity3.setName(UserRole.BLACKLISTED);
        roleRepository.save(roleEntity);
        roleRepository.save(roleEntity1);
        roleRepository.save(roleEntity2);
        roleRepository.save(roleEntity3);
    }

    public void clearAllTestData(){
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
