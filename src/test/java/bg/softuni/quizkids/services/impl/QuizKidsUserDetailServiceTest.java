package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.exceptions.UserNotFoundException;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizKidsUserDetailServiceTest {

    private QuizKidsUserDetailService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new QuizKidsUserDetailService(mockUserRepository);
    }

    @Test
    void testUserNotFoundThrowException() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("username"));
    }

    @Test
    void userFound() {
        UserEntity testUser = createTestUser();

        when(mockUserRepository.findByUsername("username"))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = serviceToTest.loadUserByUsername("username");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(),userDetails.getUsername(),
                "Username is not mapped properly");
        Assertions.assertEquals(testUser.getPassword(),userDetails.getPassword());
        Assertions.assertEquals(1,userDetails.getAuthorities().size());
        Assertions.assertTrue(containAuthority(userDetails,"ROLE_" + UserRole.ADMIN),
                "The user is not ADMIN");
    }

    private static boolean containAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }


    private static UserEntity createTestUser() {
        UserEntity testUser = new UserEntity();
        testUser.setFirstName("firstName");
        testUser.setLastName("lastName");
        testUser.setUsername("username");
        testUser.setPassword("topsecret");

        Role role = new Role();
        role.setName(UserRole.ADMIN);

        testUser.setRole(role);

        return testUser;
    }

}