package com.blogspot.pbetkier.basic;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.junit.Test;

import java.util.Date;

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

    @Test
    public void shouldGiveDefaultValueForNull() {
        // given
        Date startDate = null;
        Date currentDate = new Date();

        // when
        Date resolvedStartDate = Objects.firstNonNull(startDate, currentDate);

        // then
        assertThat(resolvedStartDate).isEqualTo(currentDate);
    }

    // Optional
}
