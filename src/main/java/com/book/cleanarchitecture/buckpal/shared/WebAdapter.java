package com.book.cleanarchitecture.buckpal.shared;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WebAdapter {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
