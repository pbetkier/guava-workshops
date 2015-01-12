package com.blogspot.pbetkier.basic;

import com.blogspot.pbetkier.Person;
import org.junit.Test;

import static com.blogspot.pbetkier.PersonBuilder.person;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.when;

public class PreconditionsTest {

    @Test
    public void shouldFailIfConstructingWithNullName() {
        // when
        when(person().name(null)).build();

        // then
        then(caughtException())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Name cannot be null.");
    }

    @Test
    public void shouldFailIfRenamingToNull() {
        // given
        Person person = person().name("Anna").build();

        // when
        when(person).renameTo(null);

        // then
        then(caughtException())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cannot rename to null.");
    }

    @Test
    public void shouldFailIfConstructingWithNegativeAge() {
        // when
        when(person().age(-5)).build();

        // then
        then(caughtException())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Age cannot be less than 0 (was -5).");
    }

    @Test
    public void shouldFailIfIncreasedAgeIsNotGreaterThanPrevious() {
        // given
        Person person = person().age(18).build();

        // when
        when(person).increaseAgeTo(15);

        // then
        then(caughtException())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New age (15) is less than previous (18).");
    }

}
