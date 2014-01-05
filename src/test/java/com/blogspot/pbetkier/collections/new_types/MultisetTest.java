package com.blogspot.pbetkier.collections.new_types;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MultisetTest {

    @Test
    public void shouldCountWordOccurrences_JDK() {
        // given
        List<String> words = Arrays.asList("alpha", "alpha", "beta", "alpha", "beta");

        // when
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            Integer count = wordCounts.get(word);
            if (count == null) {
                wordCounts.put(word, 1);
            } else {
                wordCounts.put(word, count + 1);
            }
        }

        // then
        assertThat(wordCounts.get("alpha")).isEqualTo(3);
        assertThat(wordCounts.get("beta")).isEqualTo(2);
    }

    @Test
    public void shouldCountWordOccurrences_Guava() {
        // given
        List<String> words = ImmutableList.of("alpha", "alpha", "beta", "alpha", "beta");

        // when
        Multiset<String> wordsMultiset = HashMultiset.create(words);

        // then
        assertThat(wordsMultiset.count("alpha")).isEqualTo(3);
        assertThat(wordsMultiset.count("beta")).isEqualTo(2);
    }

    // find a bug in JDK-based code above :)

}
