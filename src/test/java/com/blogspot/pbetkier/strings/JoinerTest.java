package com.blogspot.pbetkier.strings;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class JoinerTest {

    @Test
    public void shouldJoinIntegersToCommaSeparatedString() {
        // when
        String joined = Joiner.on(", ").join(Lists.newArrayList(1, 4, 2, 2));

        // then
        assertThat(joined).isEqualTo("1, 4, 2, 2");
    }

    @Test
    public void shouldFailIfJoiningNullInteger() {
        // when
        catchException(Joiner.on(", ")).join(Lists.newArrayList(1, 4, null, 2));

        // then
        assertThat(caughtException()).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldJoinWithPlaceholderForNulls() {
        // when
        String joined = Joiner.on(", ").useForNull("-").join(Lists.newArrayList(1, 4, null, 2));

        // then
        assertThat(joined).isEqualTo("1, 4, -, 2");
    }

    @Test
    public void shouldJoinSkippingNulls() {
        // when
        String joined = Joiner.on(", ").skipNulls().join(Lists.newArrayList(1, 4, null, 2));

        // then
        assertThat(joined).isEqualTo("1, 4, 2");
    }

    @Test
    public void shouldJoinKeyValuePairs() {
        // given
        Map<String, Integer> nameToAge = new TreeMap<>();
        nameToAge.put("Ania", 11);
        nameToAge.put("Basia", 27);
        nameToAge.put("Celina", 21);

        // when
        String joined = Joiner.on(", ").withKeyValueSeparator(" is ").join(nameToAge);

        // then
        assertThat(joined).isEqualTo("Ania is 11, Basia is 27, Celina is 21");
    }

}
