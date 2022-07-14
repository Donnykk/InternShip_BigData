package com.ct_common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * 参照对象注解
 */
@Target({TYPE})//表示注解只能用在类上
@Retention(RetentionPolicy.RUNTIME)//表示策略，什么时候用
public @interface TableRef {
    String value(); //增加一个value
}
