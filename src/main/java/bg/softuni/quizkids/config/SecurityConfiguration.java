package bg.softuni.quizkids.config;

import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.impl.QuizKidsUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/","/users/login","/users/registration").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                fromLogin -> {
                    fromLogin.loginPage("/users/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/home")
                            .failureForwardUrl("/");
                }
        ).logout(
                logout -> {
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);
                }
        );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new QuizKidsUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
