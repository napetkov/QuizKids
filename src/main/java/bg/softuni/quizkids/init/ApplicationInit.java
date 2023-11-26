package bg.softuni.quizkids.init;

import bg.softuni.quizkids.services.CategoryService;
import bg.softuni.quizkids.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInit implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;

    public ApplicationInit(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.init();
        categoryService.init();
//        userService.initBlacklisted();
    }


}
