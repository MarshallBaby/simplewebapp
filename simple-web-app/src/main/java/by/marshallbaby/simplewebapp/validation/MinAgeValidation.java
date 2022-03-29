package by.marshallbaby.simplewebapp.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidation implements ConstraintValidator<MinAge, LocalDate> {

    Logger logger = LoggerFactory.getLogger(MinAgeValidation.class);

    private int min;

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return Period.between(localDate, LocalDate.now()).getYears() >= min;
    }
}
