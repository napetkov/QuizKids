package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByRole(Role role);
}
