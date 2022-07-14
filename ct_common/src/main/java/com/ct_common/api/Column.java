package com.ct_common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD}) //表示注解只能用在属性上
@Retention(RetentionPolicy.RUNTIME) //表示策略，什么时候用
public @interface Column {
    String family() default "info";

    String column() default "";
}
