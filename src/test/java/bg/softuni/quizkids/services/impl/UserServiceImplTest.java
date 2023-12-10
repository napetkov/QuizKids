package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.TestInit.TestDataInit;
import bg.softuni.quizkids.exceptions.UserNotUniqueException;
import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl userServiceToTest;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Mock
    private final ModelMapper modelMapper = new ModelMapper();

    private final TestDataInit testDataInit = new TestDataInit();


    @BeforeEach
    void setUp() {
        userServiceToTest = new UserServiceImpl(
                mockUserRepository,
                mockRoleRepository,
                passwordEncoder,
                modelMapper);
    }

    @Test
    void testEnteredPasswordDoNotConfirm() {
        UserRegisterBindingModel userRegisterBindingModel = createUserRegisterBindingModel();
        userRegisterBindingModel.setConfirmPassword("test");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userServiceToTest.registerUser(userRegisterBindingModel));
    }

    @Test
    void testUsernameNotUnique() {
        UserRegisterBindingModel userRegisterBindingModel = createUserRegisterBindingModel();
        userRegisterBindingModel.setUsername("adminPesho");
        UserEntity adminPesho = testDataInit.createAdminPesho();


        when(mockUserRepository.findByUsername("adminPesho"))
                .thenReturn(Optional.of(adminPesho));

        Assertions.assertThrows(UserNotUniqueException.class,
                () -> userServiceToTest.registerUser(userRegisterBindingModel));
    }

    @Test
    void testRegistrationUser(){
        UserRegisterBindingModel userRegisterBindingModel = createUserRegisterBindingModel();

        when(mockUserRepository.findByUsername("username"))
                .thenReturn(Optional.empty());

        userServiceToTest.registerUser(userRegisterBindingModel);

        verify(mockUserRepository,times(1)).save(any(UserEntity.class));
    }
    
    @Test
    void testGetAllUsersWithRole(){
        Role role = new Role();
        role.setName(UserRole.USER);

        UserEntity user1 = new UserEntity();
        user1.setUsername("userIvan");
        user1.setRole(role);

        UserEntity user2 = new UserEntity();
        user2.setUsername("adminPesho");
        user2.setRole(role);

        List<UserEntity> userEntities = List.of(user1,user2);

        UserEntityDTO userEntityDTO1 = new UserEntityDTO();
        userEntityDTO1.setUsername("userIvan");
        userEntityDTO1.setRole(role);

        UserEntityDTO userEntityDTO2 = new UserEntityDTO();
        userEntityDTO1.setUsername("adminPesho");
        userEntityDTO1.setRole(role);

        List<UserEntityDTO> expectedUserDTOs = List.of(userEntityDTO1, userEntityDTO2);

        when(mockRoleRepository.findByName(UserRole.USER)).thenReturn(role);
        when(mockUserRepository.findAll()).thenReturn(userEntities);
        when(mockUserRepository.findAllByRole(role)).thenReturn(userEntities);



        when(modelMapper.map(user1, UserEntityDTO.class)).thenReturn(userEntityDTO1);
        when(modelMapper.map(user2, UserEntityDTO.class)).thenReturn(userEntityDTO2);

        List<UserEntityDTO> result = userServiceToTest.getAllUsers();
        assertThat(result).hasSameElementsAs(expectedUserDTOs);

        List<UserEntityDTO> resultUsersWithRole = userServiceToTest.getAllUsersWithRole(UserRole.USER);

        assertThat(resultUsersWithRole).hasSize(2);
    }

    @Test
    void testUpdateUserRole(){
        Long userId = 1L;
        String newRoleName = "ADMIN";

        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        Role newRole = new Role();
        newRole.setName(UserRole.ADMIN);

        when(mockUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(mockRoleRepository.findByName(UserRole.ADMIN)).thenReturn(newRole);

        userServiceToTest.updateUserRole(userId, newRoleName);

        verify(mockUserRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserRoleUserNotFound(){
        Long userId = 1L;
        String newRoleName = "ADMIN";

        when(mockUserRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userServiceToTest.updateUserRole(userId, newRoleName));

        verify(mockUserRepository, never()).save(any(UserEntity.class));
    }



    private UserRegisterBindingModel createUserRegisterBindingModel() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername("username");
        userRegisterBindingModel.setPassword("topsecret");
        userRegisterBindingModel.setConfirmPassword("topsecret");
        userRegisterBindingModel.setFirstName("Pesho");
        userRegisterBindingModel.setLastName("Petrov");
        userRegisterBindingModel.setEmail("pesho@example.com");
        userRegisterBindingModel.setAge(25);
        userRegisterBindingModel.setCity("Sofia");

        return userRegisterBindingModel;
    }

}