package org.example.jdk.lambda;

import org.elasticsearch.common.TriFunction;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class lambda {

    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("小明", 16),
                new Student("小红",14),
                new Student("小兰",15),
                new Student("小李",18),
                new Student("小张",14),
                new Student("小林",15)
        );

        // 引用静态方法
        studentList.sort(Student::compareByAge);
        studentList.forEach(student -> System.out.println(student.getName()));

        // lambda表达式
        Function<Student, Integer> age = Student::getAge;
        System.out.println(age.apply(studentList.get(0)));

        Function<Integer,Integer> function1 = Math::abs;
        System.out.println(function1.apply(-123));

        Student student = new Student("小小",15);
        Supplier<String> supplier2 = student::getName;
        System.out.println(supplier2.get());

        Comparator<String> comparator = String::compareTo;
        System.out.println(comparator.compare("sike","sk"));

        List<String> list = Arrays.asList("xiaoming","xiaohong","xiaoli","xiaodu");
        //list.forEach(name -> name.toUpperCase());
        //list.forEach(String::toUpperCase);
        list.forEach(name -> System.out.println(name.toUpperCase()));

        // 无参构造
        Supplier<Student> studentSupplier = Student::new;

        // 有参构造 一个参数
        Function<String, Student> function = Student::new;

        // 有参构造 二个参数
        BiFunction<String, Integer, Student> biFunction = Student::new;

        // 多个参数就需要自己写接口
        TriFunction<String, Integer, Integer, Student> triFunction = Student::new;

        // 数组引用
        Function<Integer, String[]> function2 = String[]::new;
        String[] apply = function2.apply(10);







    }

}
