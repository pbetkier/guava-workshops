package com.blogspot.pbetkier.collections.functional;

import com.blogspot.pbetkier.Person;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.blogspot.pbetkier.PersonBuilder.person;
import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringCollectionsTest {

    @Test
    public void shouldFilterOutPersonsUnder21_JDK() {
        // given
        Person over21 = person().age(30).build();
        Person under21 = person().age(12).build();
        List<Person> persons = Lists.newArrayList(over21, under21);

        // when
        List<Person> only21OrMore = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() >= 21) {
                only21OrMore.add(person);
            }
        }

        // then
        assertThat(only21OrMore).containsOnly(over21);
    }

    @Test
    public void shouldFilterOutPersonsUnder21_Guava() {
        // given
        Person over21 = person().age(30).build();
        Person under21 = person().age(12).build();
        List<Person> persons = Lists.newArrayList(over21, under21);

        // when
        Iterable<Person> only21OrMore = Iterables.filter(persons, new Predicate<Person>() {
            @Override
            public boolean apply(Person p) {
                return p.getAge() >= 21;
            }
        });

        // then
        assertThat(only21OrMore).containsOnly(over21);
    }

    @Test
    public void shouldFilterOutBlacklistedUsersFromRequestingSet() {
        // given
        Set<String> requesting = Sets.newHashSet("Bogdan", "Adam", "Ala", "Basia");
        Set<String> blacklisted = Sets.newHashSet("Bogdan", "Basia");

        // when
        Iterable<String> allowed = Iterables.filter(requesting, not(in(blacklisted)));

        // then
        assertThat(allowed).containsOnly("Adam", "Ala");
    }

    @Test
    public void shouldCheckNotAllPersonsAreAdult_JDK() {
        // given
        Person firstAdult = person().build();
        Person secondAdult = person().build();
        Person notAdult = person().notAdult().build();
        List<Person> persons = Lists.newArrayList(firstAdult, secondAdult, notAdult);

        // when
        boolean allAreAdult = true;
        for (Person person : persons) {
            if (!person.isAdult()) {
                allAreAdult = false;
                break;
            }
        }

        // then
        assertThat(allAreAdult).isFalse();
    }

    @Test
    public void shouldCheckNotAllPersonsAreAdult_Guava() {
        // given
        Person firstAdult = person().build();
        Person secondAdult = person().build();
        Person notAdult = person().notAdult().build();
        List<Person> persons = Lists.newArrayList(firstAdult, secondAdult, notAdult);

        // when
        boolean allAreAdult = Iterables.all(persons, new Predicate<Person>() {
            @Override
            public boolean apply(Person p) {
                return p.isAdult();
            }
        });

        // then
        assertThat(allAreAdult).isFalse();
    }

    @Test
    public void shouldCheckThereIsAnAdult() {
        // given
        Person firstAdult = person().build();
        Person secondAdult = person().build();
        Person notAdult = person().notAdult().build();
        List<Person> persons = Lists.newArrayList(firstAdult, secondAdult, notAdult);

        // when
        boolean hasAdult = Iterables.any(persons, new Predicate<Person>() {
            @Override
            public boolean apply(Person p) {
                return p.isAdult();
            }
        });

        // then
        assertThat(hasAdult).isTrue();
    }


}
