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
@Target(ElementType.FIELD) //注释属性
public @interface Datas {
    String cilumnName();
    String type();
    int length();
}
