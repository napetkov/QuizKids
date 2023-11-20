package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.UserRole;
import org.springframework.stereotype.Service;


public interface RoleService {
    Role getRoleByName(String name);
}
