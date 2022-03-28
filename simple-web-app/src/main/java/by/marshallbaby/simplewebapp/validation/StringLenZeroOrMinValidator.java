package by.marshallbaby.simplewebapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringLenZeroOrMinValidator implements ConstraintValidator<StringLenZeroOrMin, String> {

    private int min;

    @Override
    public void initialize(StringLenZeroOrMin constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.length() == 0 || value.length() >= min;
    }
}
