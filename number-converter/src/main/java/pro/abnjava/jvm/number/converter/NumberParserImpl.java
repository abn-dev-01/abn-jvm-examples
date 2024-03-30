package pro.abnjava.jvm.number.converter;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.util.Utils;

public class NumberParserImpl implements NumberParser {

    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    @Override
    public BigDecimal toNumber(String input) {

        var isSpaceAndComma = input.contains(SPACE) && input.contains(COMMA);
        var isSpaceAndDot = input.contains(SPACE) && input.contains(DOT);

        // Removing currency symbols and spaces
        String cleanedInput = input.replaceAll("[€$\\s]", EMPTY);

        // Determining the sign of a number
        boolean isNegative = cleanedInput.contains("(") || cleanedInput.endsWith("-") || cleanedInput.startsWith("-");
        cleanedInput = cleanedInput.replaceAll("[()\\-]", EMPTY);

        // Replacing delimiter characters
        // Assuming that if both '.' and ',' are present, ',' is the decimal separator
        if (cleanedInput.contains(COMMA) && cleanedInput.contains(DOT)) {
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
        } else {
            // Assuming '.' as the thousand separator if ',' is the decimal separator
            if (cleanedInput.contains(COMMA)) { // repalce comma with dot
                cleanedInput = replaceCommaWithDot(cleanedInput);
            }
            final int countDots = Utils.countChars(cleanedInput, DOT.charAt(0));
            // determining the position of the thousand separator - if the dot before 3 characters:
            // 1.234 - thousands separator is a dot
            // 1.23 - decimal dot
            var indDot = cleanedInput.indexOf(DOT);
            if (!(isSpaceAndComma || isSpaceAndDot) && cleanedInput.length() - indDot -1 ==3) {
                cleanedInput = cleanedInput.replace(DOT, EMPTY);
            } else if (countDots > 1) {
                // last occurrence of DOT is a decimal separator
                cleanedInput = cleanedInput.replace(DOT, EMPTY);
            }
        }

        // Convert to number
        BigDecimal number = new BigDecimal(cleanedInput);
        return isNegative ? number.negate() : number;
    }

    private static String replaceCommaWithDot(String cleanedInput) {
        cleanedInput = cleanedInput.replace(COMMA, DOT);
        return cleanedInput;
    }
}
