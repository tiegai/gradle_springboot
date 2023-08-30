package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Retention 表示生命周期
 * Target 表示使用的目标范围
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //注释类
public @interface IsUser {
    String value() ;
}
