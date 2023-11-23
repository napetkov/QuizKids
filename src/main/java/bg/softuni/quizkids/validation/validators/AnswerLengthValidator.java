package bg.softuni.quizkids.validation.validators;

import bg.softuni.quizkids.models.binding.AddAnswerBindingModel;
import bg.softuni.quizkids.validation.anotations.AnswerLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnswerLengthValidator implements ConstraintValidator<AnswerLength, Set<AddAnswerBindingModel>> {
    private int min;
    private int max;

    @Override
    public void initialize(AnswerLength annotation) {
        this.min = annotation.min();
        this.max = annotation.max();

    }

    @Override
    public boolean isValid(Set<AddAnswerBindingModel> answers, ConstraintValidatorContext context) {

        for (AddAnswerBindingModel answer : answers) {
            if (answer == null || answer.getContent().length() < min || answer.getContent().length() > max){
                return false;
            }
        }

        return true;
    }
}
