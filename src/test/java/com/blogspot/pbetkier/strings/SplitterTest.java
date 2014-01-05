package com.blogspot.pbetkier.strings;

import com.google.common.base.Splitter;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitterTest {

    @Test
    public void shouldSplitSimpleStringOnAGivenSeparator() {
        // given
        String toSplit = "a,b,c";

        // when
        Iterable<String> split = Splitter.on(",").split(toSplit);

        // then
        assertThat(split).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldSplitStringWithMissingElementsOnAGivenSeparator() {
        // given
        String toSplit = ",a,,b,";

        // when
        Iterable<String> split = Splitter.on(",").split(toSplit);

        // then
        assertThat(split).containsExactly("", "a", "", "b", "");
    }

    @Test
    @Ignore
    public void quizHowWouldJDKSplitBehave() {
        // given
        String toSplit = ",a,,b,";

        // when
        String[] split = toSplit.split(",");

        // then
        assertThat(split).containsExactly("", "a", "", "b", ""); // A (same as Splitter)
        assertThat(split).containsExactly("", "a", "", "b");     // B
        assertThat(split).containsExactly("a", "", "b");         // C
        assertThat(split).containsExactly("a", null, "b");       // D
        assertThat(split).containsExactly("a", "b");             // E
    }

    @Test
    public void shouldOmitEmptyElements() {
        // given
        String toSplit = ",a,,b,";

        // when
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().split(toSplit);

        // then
        assertThat(split).containsExactly("a", "b");
    }

    @Test
    public void shouldTrimResults() {
        // given
        String toSplit = "  a,b   ,c";

        // when
        Iterable<String> split = Splitter.on(",").trimResults().split(toSplit);

        // then
        assertThat(split).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldSplitIntoSubstrings() {
        // given
        String toSplit = "abcdefgh";

        // when
        Iterable<String> split = Splitter.fixedLength(3).split(toSplit);

        // then
        assertThat(split).containsExactly("abc", "def", "gh");
    }

}
