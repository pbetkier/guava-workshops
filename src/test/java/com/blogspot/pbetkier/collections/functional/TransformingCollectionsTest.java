package com.blogspot.pbetkier.collections.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.blogspot.pbetkier.collections.functional.PersonBuilder.person;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformingCollectionsTest {

    @Test
    public void shouldExtractPersonNames() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        List<Person> persons = Lists.newArrayList(antek, basia);

        // when
        List<String> names = new ArrayList<>();
        for (Person person : persons) {
            names.add(person.getName());
        }
//        List<String> names = Lists.transform(persons, new Function<Person, String>() {
//            @Override
//            public String apply(Person p) {
//                return p.getName();
//            }
//        });

        // then
        assertThat(names).containsExactly("Antek", "Basia");
    }

    @Test
    public void shouldMoreComplexExample() {
        // given

        // when

        // then
    }

}
