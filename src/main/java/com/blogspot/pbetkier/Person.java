package com.blogspot.pbetkier;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        Preconditions.checkNotNull(name, "Name cannot be null.");
        Preconditions.checkArgument(age >= 0, "Age cannot be less than 0 (was %s).", age);

        this.name = name;
        this.age = age;
    }

    public void renameTo(String name) {
        this.name = Preconditions.checkNotNull(name, "Cannot rename to null.");
    }

    public void increaseAgeTo(int age) {
        Preconditions.checkArgument(age > this.age, "New age (%s) is less than previous (%s).", age, this.age);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    /**
     * e.g. Person{name=Alicja, age=25}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return Objects.equals(name, otherPerson.name) &&
                Objects.equals(age, otherPerson.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}
