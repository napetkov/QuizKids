package bg.softuni.quizkids.config;

import bg.softuni.quizkids.models.enums.UserRole;
import bg.softuni.quizkids.repository.UserRepository;
import bg.softuni.quizkids.services.impl.QuizKidsUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public final String rememberMeKey;

    public SecurityConfiguration(@Value("${app.remember.me.key}")String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable().csrf().disable();

        httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/api/admin").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/questions/add","/api/questions").hasAnyRole(UserRole.MODERATOR.name(), UserRole.ADMIN.name())
                        .anyRequest().authenticated()
        ).formLogin(
                fromLogin -> {
                    fromLogin.loginPage("/users/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/home", true)
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);
                }
        ).rememberMe(rememberMe -> {
            rememberMe
                    .key(rememberMeKey)
                    .rememberMeParameter("rememberMe")
                    .rememberMeCookieName("rememberMe");
        });

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new QuizKidsUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
