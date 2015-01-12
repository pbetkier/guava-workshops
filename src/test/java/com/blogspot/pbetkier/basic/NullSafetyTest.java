package com.blogspot.pbetkier.basic;

import com.google.common.base.Strings;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NullSafetyTest {

    @Test
    public void shouldCheckIfNullOrEmptyString() {
        assertThat(Strings.isNullOrEmpty("")).isTrue();
        assertThat(Strings.isNullOrEmpty(null)).isTrue();
        assertThat(Strings.isNullOrEmpty("not empty")).isFalse();
    }

    @Test
    public void shouldConvertNullToEmptyString() {
        assertThat(Strings.nullToEmpty(null)).isEqualTo("");
        assertThat(Strings.nullToEmpty("other")).isEqualTo("other");
    }

    /**
     * Optionals are in JDK since Java 8,
     * use Guava if you're stuck with an earlier version
     */
    @Test
    public void shouldCreateOptionalWithAValue_JDK8() {
        // given
        Integer value = 5;

        // when
        Optional<Integer> possibleValue = Optional.of(value);

        // then
        assertThat(possibleValue.isPresent()).isTrue();
        assertThat(possibleValue.get()).isEqualTo(5);
    }

    @Test
    public void shouldCreateEmptyOptional_JDK8() {
        // when
        Optional<Integer> possibleValue = Optional.empty();

        // then
        assertThat(possibleValue.isPresent()).isFalse();
    }

    @Test
    public void shouldCreatePotentiallyEmptyOptional_JDK8() {
        // given
        Integer value = null;

        // when
        Optional<Integer> possibleValue = Optional.ofNullable(value);

        // then
        assertThat(possibleValue.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnDefaultValueOnlyIfOptionalEmpty_JDK8() {
        assertThat(Optional.of(5).orElse(0)).isEqualTo(5);
        assertThat(Optional.empty().orElse(0)).isEqualTo(0);
    }

    @Test
    public void shouldReturnDefaultValueFullExample_JDK8() {
        // given
        ZonedDateTime startDate = null;
        ZonedDateTime currentDate = ZonedDateTime.now();

        // when
        ZonedDateTime resolvedStartDate = Optional.ofNullable(startDate).orElse(currentDate);

        // then
        assertThat(resolvedStartDate).isEqualTo(currentDate);
    }

    @Test
    public void shouldAllowForMappingAndFiltering_JDK8() {
        assertThat(Optional.of(25).map(Math::sqrt).get()).isEqualTo(5);
        assertThat(Optional.of(-3).filter(x -> x > 0).isPresent()).isFalse();
    }

}
