package org.example.chain.entity;

public class MessageEntity {

    private String name;

    private String gender; // 0：male；1：female

    private Integer age; //

    private String vip; // 0:非vip;1:vip;default=0

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
