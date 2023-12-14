package bg.softuni.quizkids.config;

import bg.softuni.quizkids.controller.interseptor.BlacklistInterceptor;
import bg.softuni.quizkids.controller.interseptor.NavBarNotificationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final BlacklistInterceptor blacklistInterceptor;
    private final NavBarNotificationInterceptor navBarNotificationInterceptor;

    public WebConfiguration(BlacklistInterceptor blacklistInterceptor, NavBarNotificationInterceptor navBarNotificationInterceptor) {
        this.blacklistInterceptor = blacklistInterceptor;
        this.navBarNotificationInterceptor = navBarNotificationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(blacklistInterceptor);
        registry.addInterceptor(navBarNotificationInterceptor);
    }
}
