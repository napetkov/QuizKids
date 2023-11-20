package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.RoleRepository;
import bg.softuni.quizkids.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
   private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(UserRole.valueOf(name));
    }
}
