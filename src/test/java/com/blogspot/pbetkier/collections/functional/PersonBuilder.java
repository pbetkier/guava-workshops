package com.blogspot.pbetkier.collections.functional;

public class PersonBuilder {

    private String name;

    private int age;

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

    public Person build() {
        Person person = new Person(name, age);
        return person;
    }
}
