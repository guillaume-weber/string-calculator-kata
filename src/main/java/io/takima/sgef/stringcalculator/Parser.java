package io.takima.sgef.stringcalculator;

import static java.util.Arrays.stream;

public class Parser {

    private static final String DEFAULT_SEPARATORS_REGEXP = "[,\n]";
    private static final String CUSTOM_SEPARATOR_DELIMITERS_REGEXP = "//|\n";
    private static final String CUSTOM_SEPARATOR_INDICATOR = "//";
    private static final String NEW_LINE = "\n";

    public int[] parse(String input) {
        String separator = getSeparator(input);
        String numbersString = getNumbersString(input);

        return stream(numbersString.split(separator))
                .filter(numberString -> !numberString.isEmpty())
                .mapToInt(numberString -> {
                    try {
                        return Integer.parseInt(numberString);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .toArray();
    }

    private String getSeparator(String input) {
        if (input.startsWith(CUSTOM_SEPARATOR_INDICATOR)) {
            String[] customSeparator = input.split(CUSTOM_SEPARATOR_DELIMITERS_REGEXP);
            if (customSeparator.length > 1) {
                return customSeparator[1];
            }
        }
        return DEFAULT_SEPARATORS_REGEXP;
    }

    private String getNumbersString(String input) {
        if (input.startsWith(CUSTOM_SEPARATOR_INDICATOR)) {
            int firstNumberIndex = input.indexOf(NEW_LINE) + 1;
            if (input.length() > firstNumberIndex) {
                return input.substring(firstNumberIndex);
            }
            // only the custom separator was sent
            return "";
        }
        return input;
    }

}
