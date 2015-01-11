package com.blogspot.pbetkier.collections.utilities;

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
