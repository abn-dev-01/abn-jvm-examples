package pro.abnjava.jvm.converter.numbers;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberParser;

class StandardNumberParserTest {

    private final NumberParser numberParser = new StandardNumberParser();

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,234.56/1234.56",
        "1,234,555.56/1234555.56",
        "1.234,56/1234.56",
        "1.234.666,56/1234666.56",
        "(1,234.56)/-1234.56",
        "(1,234,567.56)/-1234567.56",
    }, delimiter = '/')
    void toNumber(String input, String expected) {
        var numberParserResultOpt = numberParser.parse(input);
        Assertions.assertTrue(numberParserResultOpt.isPresent());

        final var parserResult = numberParserResultOpt.get();
        Assertions.assertNotNull(parserResult.getResult());
        Assertions.assertEquals(new BigDecimal(expected), parserResult.getResult());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1/1",
        "1000/1000",
    }, delimiter = '/')
    void toNumberEasy(String input, String expected) {
        var number = numberParser.parse(input);
        Assertions.assertTrue(number.isPresent());
        Assertions.assertEquals(new BigDecimal(expected), number.get().getResult());
    }

    /**
     * When using a thousand separator for the number 0.001002003004, it depends on the convention being used.
     * Different regions use different characters as thousand separators, and the placement of the separator
     * can vary based on the length of the fractional part. Here are some possible ways to represent this number
     * with a thousand separator:
     * <p>
     * 1. **No separator for fractional part**: Since the number has more than three digits after the decimal point,
     * typically, no thousand separator is used in the fractional part.
     * - `0.001002003004` (no thousand separator in the fractional part)
     * <p>
     * 2. **Using a comma as the thousand separator** (common in many English-speaking countries):
     * - `0.001,002,003,004`
     * <p>
     * 3. **Using a period as the thousand separator** (common in many European countries):
     * - `0.001.002.003.004`
     * <p>
     * 4. **Using a space as the thousand separator** (used in some countries as a more universal option):
     * - `0.001 002 003 004`
     * <p>
     * It's important to note that the use of thousand separators in the fractional part of a number is not standard
     * practice in most contexts. Typically, thousand separators are used to group digits in the integer part
     * of a number to improve readability. For the fractional part, the digits are usually left ungrouped.
     *
     * @param input
     * @param expected
     */
    @ParameterizedTest
    @CsvSource(value = {
        "0.001/0.001",
        "0.001 002 003/0.001002003",
    }, delimiter = '/')
    void toNumberDecimals(String input, String expected) {
        var numberParserResultOpt = numberParser.parse(input);
        Assertions.assertTrue(numberParserResultOpt.isPresent());

        final ParserResult<BigDecimal> resultOpt = numberParserResultOpt.get();
        final BigDecimal result = resultOpt.getResult();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(new BigDecimal(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "$1,234.56/1234.56",
        "1,234.56 $/1234.56",
    }, delimiter = '/')
    void toNumberUsdTest(String input, String expected) {
        var numberParserOpt = numberParser.parse(input);
        Assertions.assertTrue(numberParserOpt.isPresent());

        final ParserResult<BigDecimal> parserResult = numberParserOpt.get();
        final BigDecimal result = parserResult.getResult();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(new BigDecimal(expected), result);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "-1 234 567.567/-1234567.567",
        "(1,234)/-1234",
        "(1,234,567)/-1234567",
        "-1 234.56/-1234.56",
        "- 1 234 567.56/-1234567.56",
        "1 234 567.56-/-1234567.56",
        "1 234 567.56 -/-1234567.56",
    }, delimiter = '/')
    void toNumberSpaceTest(String input, String expected) {
        var numberParserOpt = numberParser.parse(input);
        Assertions.assertTrue(numberParserOpt.isPresent());

        final var parserResult = numberParserOpt.get();
        final BigDecimal result = parserResult.getResult();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(new BigDecimal(expected), result);
    }

    @Test
    void isFractionalNumber() {
        Assertions.assertTrue(((StandardNumberParser) numberParser).isFractionalNumber("0.001002003004"));
        Assertions.assertTrue(((StandardNumberParser) numberParser).isFractionalNumber("00.001002003004"));
        Assertions.assertTrue(((StandardNumberParser) numberParser).isFractionalNumber("000.001002"));
    }
}
