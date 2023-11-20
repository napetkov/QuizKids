package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(UserRole userRole);
}
