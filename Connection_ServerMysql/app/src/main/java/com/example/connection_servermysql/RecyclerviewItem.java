package com.example.connection_servermysql;

public class RecyclerviewItem {
    String name;
    String address;
    int age;
    String sex;
    int id;

    public RecyclerviewItem(int id,String name, String sex, int age, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
    }
    public RecyclerviewItem(String name, String sex, int age, String address) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
