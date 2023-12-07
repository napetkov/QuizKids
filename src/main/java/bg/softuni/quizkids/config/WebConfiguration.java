package bg.softuni.quizkids.config;

import bg.softuni.quizkids.controller.interseptor.BlacklistInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final BlacklistInterceptor blacklistInterceptor;

    public WebConfiguration(BlacklistInterceptor blacklistInterceptor) {
        this.blacklistInterceptor = blacklistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(blacklistInterceptor);
    }
}
