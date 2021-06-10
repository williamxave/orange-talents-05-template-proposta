package br.com.zupacademy.projetoproposta.validatores;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {IsValid64Validator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsValid64 {

    String message() default "O campo precisa ser um base 64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}