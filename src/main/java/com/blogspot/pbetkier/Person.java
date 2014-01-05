package com.blogspot.pbetkier;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        checkNotNull(name, "Name cannot be null.");
        checkArgument(age >= 0, "Age cannot be less than 0 (was %s).", age);

        this.name = name;
        this.age = age;
    }

    public void renameTo(String name) {
        this.name = checkNotNull(name, "Cannot rename to null.");
    }

    public void increaseAgeTo(int age) {
        checkArgument(age > this.age, "New age (%s) is less than previous (%s).", age, this.age);
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .toString();
    }
}
