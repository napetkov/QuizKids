package bg.softuni.quizkids.config;

import bg.softuni.quizkids.controller.interseptor.BlacklistInterceptor;
import bg.softuni.quizkids.controller.interseptor.NotificationCountInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final BlacklistInterceptor blacklistInterceptor;
    private final NotificationCountInterceptor notificationCountInterceptor;

    public WebConfiguration(BlacklistInterceptor blacklistInterceptor, NotificationCountInterceptor notificationCountInterceptor) {
        this.blacklistInterceptor = blacklistInterceptor;
        this.notificationCountInterceptor = notificationCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(blacklistInterceptor);
        registry.addInterceptor(notificationCountInterceptor);
    }
}
