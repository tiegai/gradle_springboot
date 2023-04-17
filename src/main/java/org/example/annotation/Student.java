package org.example.annotation;

@IsUser("学生")
public class Student {

    @Datas(cilumnName="id", type="int", length=20)
    private int id;

    @Datas(cilumnName="name", type="String", length=20)
    private String name;

    @Datas(cilumnName="age", type="int", length=3)
    private int age;
}
