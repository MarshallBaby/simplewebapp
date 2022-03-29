package by.marshallbaby.simplewebapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidation implements ConstraintValidator<MinAge, LocalDate> {

    private int min;

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return Period.between(LocalDate.now(), localDate).getYears() >= min;
    }
}
