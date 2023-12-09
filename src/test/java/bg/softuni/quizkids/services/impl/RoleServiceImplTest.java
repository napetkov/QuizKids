package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.testUtils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    private RoleServiceImpl serviceToTest;
    @Mock
    private RoleRepository mockRoleRepository;


    @BeforeEach
    void setUp() {
        serviceToTest = new RoleServiceImpl(mockRoleRepository);
    }

    @Test
    void roleFound() {
        Role role = new Role();
        role.setName(UserRole.ADMIN);

        when(mockRoleRepository.findByName(UserRole.ADMIN))
                .thenReturn(role);

        Role foundRole = serviceToTest.getRoleByName("ADMIN");

        Assertions.assertEquals(role, foundRole);
    }

    @Test
    void roleNotFound() {

        Role foundRole = mockRoleRepository.findByName(UserRole.USER);

        Assertions.assertNull(foundRole);
    }
}