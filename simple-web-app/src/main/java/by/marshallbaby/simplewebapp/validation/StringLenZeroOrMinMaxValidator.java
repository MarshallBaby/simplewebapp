package by.marshallbaby.simplewebapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringLenZeroOrMinMaxValidator implements ConstraintValidator<StringLenZeroOrMinMax, String> {

    private int min;
    private int max;

    @Override
    public void initialize(StringLenZeroOrMinMax constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.length() == 0 || (value.length() >= min && value.length() <= max);
    }
}
