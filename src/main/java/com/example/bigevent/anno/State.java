package com.example.bigevent.anno;
import java.lang.annotation.*;

import jakarta.validation.Constraint;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface State {

}
