package com.blogspot.pbetkier.collections;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SetOperationsTest {

    @Test
    public void shouldFilterOutBlacklistedUsersFromRequestingSet() {
        // given
        Set<String> blacklisted = Sets.newHashSet("bogdan", "basia");
        Set<String> requesting = Sets.newHashSet("bogdan", "adam", "ala", "basia");

        // when
        Set<String> allowed = new HashSet<>();
        for (String user : requesting) {
            if (!blacklisted.contains(user)) {
                allowed.add(user);
            }
        }
//        Set<String> allowed = Sets.difference(requesting, blacklisted);

        // then
        assertThat(allowed).containsOnly("adam", "ala");
    }

    @Test
    public void shouldProvideSetsIntersection() {
        // given
        Set<String> first = Sets.newHashSet("adam", "basia");
        Set<String> second = Sets.newHashSet("basia", "celina");

        // when
        Set<String> intersection = Sets.intersection(first, second);

        // then
        assertThat(intersection).containsOnly("basia");
    }

    @Test
    public void shouldProvideSetsUnion() {
        // given
        Set<String> first = Sets.newHashSet("adam", "basia");
        Set<String> second = Sets.newHashSet("basia", "celina");

        // when
        Set<String> union = Sets.union(first, second);

        // then
        assertThat(union).containsOnly("adam", "basia", "celina");
    }

}
