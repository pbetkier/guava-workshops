package com.blogspot.pbetkier.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatingCollectionsTest {

    @Test
    public void shouldCreatePrepopulatedList() {
        // when
        List<String> created = new ArrayList<>();
        created.add("alpha");
        created.add("beta");
        created.add("gamma");

        // Lists.newArrayList("alpha", "beta", "gamma");

        // then
        assertThat(created).containsExactly("alpha", "beta", "gamma");
    }

    @Test
    public void shouldCreatePrepopulatedSet() {
        // when
        Set<Integer> created = new HashSet<>(Arrays.asList(1, 3, 5));

//        Sets.newHashSet(1, 3, 5)

        // then
        assertThat(created).containsExactly(1, 3, 5);
    }


}
