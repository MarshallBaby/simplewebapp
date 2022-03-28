package by.marshallbaby.simplewebapp.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = StringLenZeroOrMinValidator.class)
@Target({ TYPE, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface StringLenZeroOrMin {
    String message();
    int min();
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
