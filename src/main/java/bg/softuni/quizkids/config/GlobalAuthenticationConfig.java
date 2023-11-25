//package bg.softuni.quizkids.config;
//
//import bg.softuni.quizkids.repository.UserRepository;
//import bg.softuni.quizkids.services.impl.QuizKidsUserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("globalUser").password("password").roles("ADMIN");
//    }
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new QuizKidsUserDetailService(userRepository);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
