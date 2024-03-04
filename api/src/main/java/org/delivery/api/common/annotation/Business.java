package org.delivery.api.common.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface Business { //Business 어노테이션이 달린 애는 BusinessLogic을 처리할 거야 라는 룰을 만든다.

    @AliasFor(annotation = Service.class)
    String value() default "";
}
