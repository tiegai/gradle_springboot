package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention 表示生命周期
 * Target 表示使用的目标范围：
 *
 * CONSTRUCTOR:用于描述构造器
 * FIELD:用于描述符
 * LOCAL_VARIABLE:用于描述局部变量
 * METHOD:用于描述方法
 * PACKAGE:用于描述包
 * PARAMETER: 用于描述参数
 * TYPE: 用于描述类、接口（包括注解类型）或者enum声明
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //注释类
public @interface IsUser {
    String value() ;
}
