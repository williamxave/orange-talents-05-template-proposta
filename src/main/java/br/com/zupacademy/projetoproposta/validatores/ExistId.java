package br.com.zupacademy.projetoproposta.validatores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {

    String message() default "Id de proposta inválida";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();


}