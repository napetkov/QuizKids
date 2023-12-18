package bg.softuni.quizkids.validation.validators;

import bg.softuni.quizkids.models.binding.UserChangePasswordBindingModel;
import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.validation.anotations.ConfirmPasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordMatchesValidator implements ConstraintValidator<ConfirmPasswordMatches, UserRegisterBindingModel> {

    @Override
    public void initialize(ConfirmPasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
    }
}
