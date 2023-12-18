package bg.softuni.quizkids.validation.validators;

import bg.softuni.quizkids.models.binding.ConfirmPasswordBindingModel;
import bg.softuni.quizkids.models.binding.UserChangePasswordBindingModel;
import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.validation.anotations.ConfirmPasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordMatchesValidator implements ConstraintValidator<ConfirmPasswordMatches, ConfirmPasswordBindingModel> {

    @Override
    public void initialize(ConfirmPasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ConfirmPasswordBindingModel confirmPasswordBindingModel,
                           ConstraintValidatorContext constraintValidatorContext) {
        return confirmPasswordBindingModel.getPassword().equals(confirmPasswordBindingModel.getConfirmPassword());
    }
}
