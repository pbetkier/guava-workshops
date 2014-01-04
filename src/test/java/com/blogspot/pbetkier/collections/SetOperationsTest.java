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
        Set<String> requesting = Sets.newHashSet("bogdan", "adam", "ala", "basia");
        Set<String> blacklisted = Sets.newHashSet("bogdan", "basia");

        // when
        Set<String> allowed = new HashSet<>(requesting);
        allowed.removeAll(blacklisted);

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
//        Set<String> intersection = new HashSet<>(first);
//        intersection.retainAll(second);

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
//        Set<String> union = new HashSet<>(first);
//        union.addAll(second);

        Set<String> union = Sets.union(first, second);

        // then
        assertThat(union).containsOnly("adam", "basia", "celina");
    }

    @Test
    public void shouldProvideSetsSymmetricDifference() {
        // given
        Set<String> first = Sets.newHashSet("adam", "basia");
        Set<String> second = Sets.newHashSet("basia", "celina");

        // when
//        Set<String> symmetricDifference = new HashSet<>(first);
//        symmetricDifference.addAll(second);
//        Set<String> tmp = new HashSet<>(first);
//        tmp.retainAll(second);
//        symmetricDifference.removeAll(tmp);

        Set<String> symmetricDifference = Sets.symmetricDifference(first, second);

        // then
        assertThat(symmetricDifference).containsOnly("adam", "celina");
    }


}
