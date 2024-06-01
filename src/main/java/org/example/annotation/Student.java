package org.example.annotation;

@IsUser("学生")
public class Student {

    @Datas(columnName="id", type="int", length=20)
    private int id;

    @Datas(columnName="name", type="String", length=20)
    private String name;

    @Datas(columnName="age", type="int", length=3)
    private int age;
}
