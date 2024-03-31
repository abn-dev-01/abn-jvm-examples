package pro.abnjava.jvm.number.converter.numbers;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.util.Utils;

public class NumberParserStandard implements NumberParser {

    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    @Override
    public BigDecimal toNumber(String input) {

        var isSpaceAndComma = isSpaceAndComma(input);
        var isSpaceAndDot = isSpaceAndDot(input);

        // Removing currency symbols and spaces
        String cleanedInput = input.replaceAll("[€$\\s]", EMPTY);

        // Determining the sign of a number
        boolean isNegative = isNegative(cleanedInput);
        cleanedInput = cleanedInput.replaceAll("[()\\-]", EMPTY);

        // Replacing delimiter characters
        if (cleanedInput.contains(COMMA) && cleanedInput.contains(DOT)) {
            // Assuming that if both '.' and ',' are present, ',' is the decimal separator
            cleanedInput = parseWhenDotAndComma(cleanedInput);
        } else {
            cleanedInput = parseWhenCommaOrDot(cleanedInput, isSpaceAndComma, isSpaceAndDot);
        }

        // Convert to number
        BigDecimal number = new BigDecimal(cleanedInput);
        return isNegative ? number.negate() : number;
    }

    private static String parseWhenCommaOrDot(String cleanedInput, boolean isSpaceAndComma, boolean isSpaceAndDot) {
        // Assuming '.' as the thousand separator if ',' is the decimal separator
        if (cleanedInput.contains(COMMA)) { // repalce comma with dot
            cleanedInput = replaceCommaWithDot(cleanedInput);
        }
        final int countDots = Utils.countChars(cleanedInput, DOT.charAt(0));
        // determining the position of the thousand separator - if the dot before 3 characters:
        // 1.234 - thousands separator is a dot
        // 1.23 - decimal dot
        var indDot = cleanedInput.indexOf(DOT);
        boolean shouldReplaceDot = (!(isSpaceAndComma || isSpaceAndDot) && cleanedInput.length() - indDot - 1 == 3)
            || countDots > 1;

        if (shouldReplaceDot) {
            cleanedInput = cleanedInput.replace(DOT, EMPTY);
        }

        return cleanedInput;
    }

    private static String parseWhenDotAndComma(String cleanedInput) {
        // determining the position of the decimal separator - the last occurrence of '.' and ','
        var lastPosDot = cleanedInput.lastIndexOf(DOT);
        var lastPosComma = cleanedInput.lastIndexOf(COMMA);
        if (lastPosComma > lastPosDot) {
            // comma has the last occurrence - it is a decimal-separator
            cleanedInput = cleanedInput.replace(DOT, EMPTY);
            // changing the decimal separator to a dot
            cleanedInput = replaceCommaWithDot(cleanedInput);
        } else {
            // dot has the last occurrence - it is a decimal-separator
            cleanedInput = cleanedInput.replace(COMMA, EMPTY);
        }
        return cleanedInput;
    }

    private static boolean isNegative(String cleanedInput) {
        return cleanedInput.contains("(") || cleanedInput.endsWith("-") || cleanedInput.startsWith("-");
    }

    private static boolean isSpaceAndDot(String input) {
        return input.contains(SPACE) && input.contains(DOT);
    }

    private static boolean isSpaceAndComma(String input) {
        return input.contains(SPACE) && input.contains(COMMA);
    }

    private static String replaceCommaWithDot(String cleanedInput) {
        cleanedInput = cleanedInput.replace(COMMA, DOT);
        return cleanedInput;
    }
}
