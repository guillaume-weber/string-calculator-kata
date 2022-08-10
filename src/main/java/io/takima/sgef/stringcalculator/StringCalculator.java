package io.takima.sgef.stringcalculator;

import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class StringCalculator {
    private final Parser parser;

    public StringCalculator(Parser parser) {
        this.parser = parser;
    }

    public int add(String input) {
        int[] numbers = parser.parse(input);
        validate(numbers);
        return stream(numbers).sum();
    }

    private void validate(int[] numbers) throws NegativeNumberException {
        int[] negativeNumbers = stream(numbers).filter(n -> n < 0).toArray();
        if (negativeNumbers.length > 0) {
            throw new NegativeNumberException("negatives not allowed: " + stream(negativeNumbers)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", "))
            );
        }
    }

}
