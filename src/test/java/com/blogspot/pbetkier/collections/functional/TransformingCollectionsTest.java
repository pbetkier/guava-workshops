package com.blogspot.pbetkier.collections.functional;

import com.blogspot.pbetkier.Person;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.blogspot.pbetkier.PersonBuilder.person;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformingCollectionsTest {

    @Test
    public void shouldExtractPersonNames_JDK7_iterating() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        List<Person> persons = Lists.newArrayList(antek, basia);

        // when
        List<String> names = new ArrayList<>();
        for (Person person : persons) {
            names.add(person.getName());
        }

        // then
        assertThat(names).containsExactly("Antek", "Basia");
    }

    @Test
    public void shouldExtractPersonNames_Guava() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        List<Person> persons = Lists.newArrayList(antek, basia);

        // when
        List<String> names = Lists.transform(persons, new Function<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.getName();
            }
        });

        // then
        assertThat(names).containsExactly("Antek", "Basia");
    }

    @Test
    public void shouldExtractPersonNames_JDK8_streams() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        List<Person> persons = Lists.newArrayList(antek, basia);

        // when
        List<String> names = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        // then
        assertThat(names).containsExactly("Antek", "Basia");
    }

}
