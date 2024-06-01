package org.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class TestClass {

    public static void main(String[] args) throws Exception {
        //1.获取student的class对象
        Class clsStudent = Class.forName("org.example.annotation.Student");

        //2.获取类的指定注释
        IsUser clsAno = (IsUser) clsStudent.getAnnotation(IsUser.class);

        //3.获取注释内容
        System.out.println("获取注释内容:" + clsAno.value());

        //4.获取类的全部注释
        Annotation[] allAno = clsStudent.getAnnotations();
        System.out.println("获取类的全部注释:" + Arrays.stream(allAno).iterator().next().toString());

        //5.获取私有属性
        Field fieldId = clsStudent.getDeclaredField("id");
        fieldId.setAccessible(true);

        //6.获取属性的指定注释
        Datas fieAno = fieldId.getAnnotation(Datas.class);

        //7.获取注释的内容
        System.out.println("column:" + fieAno.columnName());
        System.out.println("length:" + fieAno.length());
        System.out.println("type:" + fieAno.type());

        //8.获取属性的所有注释
        Annotation[] s = fieldId.getDeclaredAnnotations();
        System.out.println("获取属性的所有注释:" + Arrays.stream(s).iterator().next().toString());

    }
}
