package com.blogspot.pbetkier.collections.utilities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatingCollectionsTest {

    @Test
    public void shouldCreatePrepopulatedCollections_JDK() {
        // when
        List<String> createdList = Arrays.asList("alpha", "beta", "gamma");
        Set<Integer> createdSet = new HashSet<>(Arrays.asList(1, 3, 5));

        // then
        assertThat(createdList).containsExactly("alpha", "beta", "gamma");
        assertThat(createdSet).containsOnly(1, 3, 5);
    }

    @Test
    public void shouldCreatePrepopulatedCollections_Guava() {
        // when
        List<String> createdList = Lists.newArrayList("alpha", "beta", "gamma");
        Set<Integer> createdSet = Sets.newHashSet(1, 3, 5);

        // then
        assertThat(createdList).containsExactly("alpha", "beta", "gamma");
        assertThat(createdSet).containsOnly(1, 3, 5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldCreatePrepopulatedImmutableCollections() {
        // given
        List<String> createdList = ImmutableList.of("alpha", "beta", "gamma");

        // throws when
        createdList.add("disallowed");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldMakeImmutableCopyOfGivenCollection() {
        // given
        Set<String> original = Sets.newHashSet("alpha", "beta", "gamma");
        Set<String> immutableCopy = ImmutableSet.copyOf(original);

        // throws when
        immutableCopy.remove("alpha");
    }

    @Test
    public void guavaImmutableSetVsJDKUnmodifiableSet() {
        // given
        Set<String> original = Sets.newHashSet("alpha", "beta", "gamma");

        Set<String> immutableCopy = ImmutableSet.copyOf(original);
        Set<String> unmodifiableSet = Collections.unmodifiableSet(original);


        // when
        original.remove("gamma");

        // then
        assertThat(immutableCopy).containsOnly("alpha", "beta", "gamma");
        assertThat(unmodifiableSet).containsOnly("alpha", "beta");
    }

}
