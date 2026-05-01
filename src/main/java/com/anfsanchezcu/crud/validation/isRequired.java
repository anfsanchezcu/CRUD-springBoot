package com.anfsanchezcu.crud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;

@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface isRequired {
  String message() default "se require (validacion utilizando anotaciones personalizadas)";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
