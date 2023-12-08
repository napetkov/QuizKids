package bg.softuni.quizkids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuizKidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizKidsApplication.class, args);
    }

}
