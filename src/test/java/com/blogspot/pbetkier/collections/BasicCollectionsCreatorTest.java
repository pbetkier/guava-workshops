package com.blogspot.pbetkier.collections;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicCollectionsCreatorTest {

    private BasicCollectionsCreator creator = new BasicCollectionsCreator();

    @Test
    public void shouldCreatePrepopulatedList() {
        // when
        List<String> created = creator.createPrepopulatedList();

        // then
        assertThat(created).containsExactly("alpha", "beta", "gamma");
    }

    @Test
    public void shouldCreatePrepopulatedSet() {
        // when
        Set<Integer> created = creator.createPrepopulatedSet();

        // then
        assertThat(created).containsExactly(1, 3, 5);
    }


}
