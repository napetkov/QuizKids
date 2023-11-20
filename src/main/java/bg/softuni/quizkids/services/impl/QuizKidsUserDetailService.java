package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.entity.Role;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class QuizKidsUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public QuizKidsUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " was not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(map(userEntity.getRole()))
                .build();
    }
    private static GrantedAuthority map(Role role){
        return new SimpleGrantedAuthority(
                "ROLE_" + role.getName().name()
        );
    }
}
