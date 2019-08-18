package com.demo.springmvc.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordNotSameValidator.class)
public @interface PasswordNotSame {

    String message() default "password not same!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
