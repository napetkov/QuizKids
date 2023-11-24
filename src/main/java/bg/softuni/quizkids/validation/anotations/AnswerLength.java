package bg.softuni.quizkids.validation.anotations;

import bg.softuni.quizkids.validation.validators.AnswerLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnswerLengthValidator.class)
public @interface AnswerLength {
    String message() default "Answer length must be between {min} and {max}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int min() default 2;
    int max() default 50;
}
