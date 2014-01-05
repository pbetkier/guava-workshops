package com.blogspot.pbetkier.basic;

import com.blogspot.pbetkier.Person;
import org.junit.Test;

import static com.blogspot.pbetkier.PersonBuilder.person;
import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTest {

    @Test
    public void shouldFailIfConstructingWithNullName() {
        // when
        catchException(person().name(null)).build();

        // then
        assertThat(caughtException())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Name cannot be null.");
    }

    @Test
    public void shouldFailIfRenamingToNull() {
        // given
        Person person = person().name("Anna").build();

        // when
        catchException(person).renameTo(null);

        // then
        assertThat(caughtException())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cannot rename to null.");
    }

    @Test
    public void shouldFailIfConstructingWithNegativeAge() {
        // when
        catchException(person().age(-5)).build();

        // then
        assertThat(caughtException())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Age cannot be less than 0 (was -5).");
    }

    @Test
    public void shouldFailIfIncreasedAgeIsNotGreaterThanPrevious() {
        Person person = person().age(18).build();

        // when
        catchException(person).increaseAgeTo(15);

        // then
        assertThat(caughtException())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New age (15) is less than previous (18).");
    }

}
