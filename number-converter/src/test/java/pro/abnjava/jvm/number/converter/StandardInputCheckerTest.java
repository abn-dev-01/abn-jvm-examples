package pro.abnjava.jvm.number.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pro.abnjava.jvm.number.converter.bankcard.BankCardParserResult;
import pro.abnjava.jvm.number.converter.numbers.NumberParserResult;

class StandardInputCheckerTest {

    private InputChecker inputChecker = new StandardInputChecker();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCheckInputWithValidBankCard() {
        assertTrue(inputChecker.checkInput("4111 1111 1111 1111").isPresent());
        assertTrue(inputChecker.checkInput("4111 1111 1111 1111").get() instanceof BankCardParserResult);
    }

    @Test
    void testCheckInputWithInvalidBankCard() {
        final Optional<ParserResult> resultType = inputChecker.checkInput("1234 5678 9012");
        assertTrue(resultType.isPresent());
        assertEquals("123456789012", resultType.get().getNumber().toString());
    }

    @Test
    void testCheckInputWithValidNumber() {
        assertTrue(inputChecker.checkInput("12345").isPresent());
        assertTrue(inputChecker.checkInput("12345").get() instanceof NumberParserResult);
    }

    @Test
    void testCheckInputWithInvalidNumber() {
        assertFalse(inputChecker.checkInput("abcde").isPresent());
    }

    @Test
    void testCheckInputEmpty() {
        assertFalse(inputChecker.checkInput("").isPresent());
    }

    @Test
    void testCheckInputWithNumber1() {
        final Optional<ParserResult> resultType = inputChecker.checkInput("1 234 567 890.12");
        assertTrue(resultType.isPresent());
        assertEquals(new BigDecimal("1234567890.12"), resultType.get().getNumber());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1 234 567 890,12/1234567890.12",
        "1.234.567.890,12/1234567890.12",
        "(1.234.567.890,12)/-1234567890.12"
    }, delimiter = '/')
    void testCheckInputWithNumber2(String arg, String expected) {
        final Optional<ParserResult> resultType = inputChecker.checkInput(arg);
        assertTrue(resultType.isPresent());
        assertEquals(new BigDecimal(expected), resultType.get().getNumber());
    }
}
