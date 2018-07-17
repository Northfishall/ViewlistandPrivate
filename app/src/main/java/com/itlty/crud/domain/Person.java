package com.itlty.crud.domain;
public class Person {
    private int personid;
    private String name;
    private int age;
    private int account;
    public Person() {
    }
    public Person(int personid, String name, int age, int account) {
        this.personid = personid;
        this.name = name;
        this.age = age;
        this.account = account;
    }
    public int getPersonid() {
        return personid;
    }
    public void setPersonid(int personid) {
        this.personid = personid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }
    public Person(String name, int age, int account) {
        this.name = name;
        this.age = age;
        this.account = account;
    }
    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}

