package bg.softuni.quizkids.TestInit;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.models.enums.UserRole;

public class TestDataInit {

    public UserEntity createUserIvan(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("userIvan");
        userEntity.setPassword("ivan");
        userEntity.setFirstName("Ivan");
        userEntity.setLastName("Ivanov");
        userEntity.setEmail("ivan@example.com");
        userEntity.setCity("Sofia");
        userEntity.setPoint(20L);
        userEntity.setLevel(Level.BEGINNER);
        Role roleUser = new Role();
        roleUser.setName(UserRole.USER);
        userEntity.setRole(roleUser);

        return userEntity;
    }
    public UserEntity createAdminPesho(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("adminPesho");
        userEntity.setPassword("Pesho");
        userEntity.setFirstName("Pesho");
        userEntity.setLastName("Petrov");
        userEntity.setEmail("pesho@example.com");
        userEntity.setCity("Sofia");
        userEntity.setPoint(20L);
        userEntity.setAge(25);
        userEntity.setLevel(Level.BEGINNER);
        Role roleUser = new Role();
        roleUser.setName(UserRole.ADMIN);
        userEntity.setRole(roleUser);

        return userEntity;
    }

    public UserEntity createModeratorGalka(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("moderatorGalka");
        userEntity.setPassword("galka");
        userEntity.setFirstName("Galka");
        userEntity.setLastName("Galkova");
        userEntity.setEmail("galka@example.com");
        userEntity.setCity("Sofia");
        userEntity.setPoint(20L);
        userEntity.setLevel(Level.BEGINNER);
        Role roleUser = new Role();
        roleUser.setName(UserRole.MODERATOR);
        userEntity.setRole(roleUser);

        return userEntity;
    }

}
