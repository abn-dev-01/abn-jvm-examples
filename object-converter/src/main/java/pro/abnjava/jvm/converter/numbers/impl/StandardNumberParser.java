package pro.abnjava.jvm.converter.numbers.impl;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.numbers.NumberValidator;
import pro.abnjava.jvm.converter.parser.EmptyResult;
import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.util.Utils;

public class StandardNumberParser implements NumberParser {

    public static final String REGEX_NUMBR = "^(?!0\\d)(?!.*([.,])\\1)(?=.*\\d)[($]?[\\d., ]+[)]?[$€]?$\n";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String USD = "$";
    public static final String EUR = "€";

    // Assumed ',' is the decimal separator and number < 1.0 can be 0.123, 00.123, 000.1234
    public static final String FRACTIONAL_REGEX = "^0+.\\d+";


    //    @Override
    public NumberValidator getValidator() {
        return new StandardNumberValidator();
    }


    /**
     * @return
     */
    @Override
    public boolean returnInput() {
        return false; // return empty when parsing failed.
    }


    @Override
    public Optional<ParserResult<BigDecimal>> parse(String input) {

        if (!getValidator().validate(input)) {
            return Optional.empty();
        }

        var isSpaceAndComma = isSpaceAndComma(input);
        var isSpaceAndDot = isSpaceAndDot(input);

        // Removing currency symbols and spaces
        String cleanedInput = input.replaceAll("[" + USD + EUR + "\\s]", EMPTY);

        // Determining the sign of a number
        boolean isNegative = isNegative(cleanedInput);
        cleanedInput = cleanedInput.replaceAll("[()\\-]", EMPTY);

        // Check if the input is a fraction like: 0.001 00.00002 000.000002222
        var isFractional = isFractionalNumber(input);

        if (!isFractional) {
            if (cleanedInput.contains(COMMA) && cleanedInput.contains(DOT)) {
                // Replacing delimiter characters
                // Assuming that if both '.' and ',' are present, ',' is the decimal separator
                cleanedInput = parseWhenDotAndComma(cleanedInput);
            } else {
                cleanedInput = parseWhenCommaOrDot(cleanedInput, isSpaceAndComma, isSpaceAndDot);
            }
        }
        // Convert to number
        BigDecimal number = new BigDecimal(cleanedInput);
        var result = isNegative ? number.negate() : number;

        return Optional.of(new NumberParserResult(result));
    }

    /*
     * Check if the input has a fraction only.
     * `input` argument is notNull, contains only digits, commas and dots.
     */
    public boolean isFractionalNumber(String input) {
        return input.matches(FRACTIONAL_REGEX);
    }

    private String parseWhenCommaOrDot(String cleanedInput, boolean isSpaceAndComma, boolean isSpaceAndDot) {
        // Assuming '.' as the thousand separator and ',' is the decimal separator
        if (cleanedInput.contains(COMMA)) {
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

    private String parseWhenDotAndComma(String cleanedInput) {
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

    private boolean isNegative(String cleanedInput) {
        return cleanedInput.contains("(") || cleanedInput.endsWith("-") || cleanedInput.startsWith("-");
    }

    private boolean isSpaceAndDot(String input) {
        return input.contains(SPACE) && input.contains(DOT);
    }

    private boolean isSpaceAndComma(String input) {
        return input.contains(SPACE) && input.contains(COMMA);
    }

    private String replaceCommaWithDot(String cleanedInput) {
        cleanedInput = cleanedInput.replace(COMMA, DOT);
        return cleanedInput;
    }
}
