package by.marshallbaby.simplewebapp.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = StringLenZeroOrMinMaxValidator.class)
@Target({ TYPE, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface StringLenZeroOrMinMax {
    String message() default "String length validation failure.";
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
