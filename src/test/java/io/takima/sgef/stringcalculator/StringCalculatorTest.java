package io.takima.sgef.stringcalculator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    private static StringCalculator stringCalculator;

    @BeforeAll
    static void init() {
        stringCalculator = new StringCalculator(new Parser());
    }

    @Nested
    @DisplayName("Step 1")
    class Step1 {

        @Test
        @DisplayName("empty string")
        void emptyString() {
            assertEquals(0, stringCalculator.add(""), "An empty string should return 0");
        }

        @Test
        @DisplayName("one number string")
        void oneFigureString() {
            assertEquals(1, stringCalculator.add("1"));
        }

        @Test
        @DisplayName("2 numbers string")
        void twoNumbersString() {
            assertEquals(3, stringCalculator.add("1,2"), "Should return the sum of both numbers");
        }

    }

    @Nested
    @DisplayName("Step 2")
    class Step2 {

        @Test
        @DisplayName("multiple numbers")
        void multipleNumbers() {
            assertEquals(14, stringCalculator.add("1,2,5,6"), "Should return the sum of all given numbers");
        }

    }

    @Nested
    @DisplayName("Step 3")
    class Step3 {

        @Test
        @DisplayName("use new line ('\\n') as a separator as well as ','")
        void withNewLineSeparator() {
            assertEquals(6, stringCalculator.add("1\n2,3"), "Should detect new line as a separator");
        }

        @Test
        @DisplayName("empty number/consecutive separator")
        void withEmptyNumber() {
            assertEquals(3, stringCalculator.add("1,\n2"), "Should skip when encountering consecutive separators");
        }

    }

    @Nested
    @DisplayName("Step 4")
    class Step4 {

        @Test
        @DisplayName("use a custom delimiter")
        void withCustomDelimiter() {
            assertEquals(3, stringCalculator.add("//;\n1;2"), "Should use the custom delimiter");
        }

        @Test
        @DisplayName("use a custom delimiter and an empty input string")
        void withCustomDelimiterAndEmptyString() {
            assertEquals(0, stringCalculator.add("//;\n"), "An empty string should return 0");
        }

    }

    @Nested
    @DisplayName("Step 5")
    class Step5 {

        @Test
        @DisplayName("throw on negative number")
        void throwsExceptionOnNegativeNumber() {
            NegativeNumberException negativeNumberException = assertThrows(NegativeNumberException.class, () -> stringCalculator.add("-1,-2"));
            assertEquals("negatives not allowed: -1, -2", negativeNumberException.getMessage());
        }

    }

}
