package com.blogspot.pbetkier.strings;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharMatchersTest {

    @Test
    public void shouldRemoveWhitespaces() {
        // given
        String unformattedText = "  Unfor  mat\nted \nText";

        // when
        String withoutWhitespace = CharMatcher.WHITESPACE.removeFrom(unformattedText);

        // then
        assertThat(withoutWhitespace).isEqualTo("UnformattedText");
    }

    @Test
    public void shouldRetainDigitsOnly() {
        // given
        String telephoneNumber = "(22)111-33 44";

        // when
        String digits = CharMatcher.DIGIT.retainFrom(telephoneNumber);

        // then
        assertThat(digits).isEqualTo("221113344");
    }

    @Test
    public void shouldCheckThatTelephoneNumberInputIsValid() {
        // given
        // parentheses, hyphens and whitespaces are allowed
        String telephoneNumber = "(22)111-33 44";

        // when
        CharMatcher telephoneMatcher = CharMatcher.DIGIT
                .or(CharMatcher.WHITESPACE)
                .or(CharMatcher.anyOf("()-"));
        boolean isCorrect = telephoneMatcher.matchesAllOf(telephoneNumber);

        // then
        assertThat(isCorrect).isTrue();
    }

}
