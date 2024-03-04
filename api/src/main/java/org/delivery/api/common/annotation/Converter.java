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
public @interface Converter { //Converter 어노테이션이 달린 아이들은 데이터를 변환해주는 용도로 사용된다.

    @AliasFor(annotation = Service.class)
    String value() default "";
}
