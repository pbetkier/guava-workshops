package com.blogspot.pbetkier;

public class PersonBuilder {

    private String name = "Default";

    private int age = 25;

    private PersonBuilder() {
    }

    public static PersonBuilder person() {
        return new PersonBuilder();
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder age(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder notAdult() {
        this.age = 11;
        return this;
    }

    public Person build() {
        Person person = new Person(name, age);
        return person;
    }
}
