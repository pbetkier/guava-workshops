package com.blogspot.pbetkier.collections.functional;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringCollectionsTest {

    @Test
    public void shouldFilterOutPersonsUnder21() {
        // given
        Person over21 = PersonBuilder.person().name("Jan").age(30).build();
        Person under21 = PersonBuilder.person().name("Jasio").age(12).build();
        List<Person> persons = Lists.newArrayList(over21, under21);

        // when
        List<Person> onlyOver21 = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() >= 21) {
                onlyOver21.add(person);
            }
        }
//        Iterable<Person> onlyOver21 = Iterables.filter(persons, new Predicate<Person>() {
//            @Override
//            public boolean apply(Person p) {
//                return p.getAge() >= 21;
//            }
//        });

        // then
        assertThat(onlyOver21).containsOnly(over21);
    }

    @Test
    public void shouldFilterOutBlacklistedUsersFromRequestingSet() {
        // given
        Set<String> blacklisted = Sets.newHashSet("Bogdan", "Basia");
        Set<String> requesting = Sets.newHashSet("Bogdan", "Adam", "Ala", "Basia");

        // when
        Iterable<String> allowed = Iterables.filter(requesting, not(in(blacklisted)));

        // then
        assertThat(allowed).containsOnly("Adam", "Ala");
    }


}
