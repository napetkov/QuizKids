package bg.softuni.quizkids.validation.anotations;

import bg.softuni.quizkids.validation.validators.ConfirmPasswordMatchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordMatchesValidator.class)
public @interface ConfirmPasswordMatches {
    String message() default "Password and Confirm password field not match!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
