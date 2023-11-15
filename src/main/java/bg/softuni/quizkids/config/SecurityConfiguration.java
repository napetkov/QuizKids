package bg.softuni.quizkids.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(
//                        // Define which urls are visible by which users
//                        authorizationRequest -> authorizationRequest
//                                // All static resources which are situated in js, images, css are available for anyone
//                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                                //Allow anyone to see the home page, the register page and login page
//                                .requestMatchers("/").permitAll()
//                                //All other request authenticated
////                                .anyRequest().authenticated()
//                );
//                .formLogin(
//                        formLogin -> {
//                            formLogin.loginPage("/index")
//                                    //redirect where we access something which is not allowed
//                                    //also this is the page where we perform login
//                                    //the names of the input fields (in our case in auth-login.html)
//                                    .usernameParameter("email")
//                                    .passwordParameter("password")
//                                    .defaultSuccessUrl("/")
//                                    .failureForwardUrl("/users/login-error");
//                        })
//                .logout(logout -> {
//                    //The URL where we should POST something in order to perform the logout
//                    logout.logoutUrl("/users/logout")
//                            //where to go when we logged out
//                            .logoutSuccessUrl("/")
//                            //invalidate the HTTP session
//                            .invalidateHttpSession(true);
//                });

        return httpSecurity.build();
    }
}
