package org.example.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class Student {

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {

    }

    public Student(String s) {
    }

    public Student(String s, Integer integer, Integer integer1) {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int compareByAge(Student a,Student b) {
        return a.getAge().compareTo(b.getAge());
    }

}
