package pro.abnjava.jvm.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pro.abnjava.jvm.converter.bankcard.impl.BankCardResult;
import pro.abnjava.jvm.converter.numbers.impl.NumberParserResult;
import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.parser.ResultType;

class StandardInputCheckerTest {

    private InputChecker inputChecker = new StandardInputChecker();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCheckInputWithValidBankCard() {
        final var result = inputChecker.checkInput("4111 1111 1111 1111");
        assertNotNull(result);
        assertTrue(result instanceof BankCardResult);
        assertEquals(new BigDecimal("4111111111111111"),result.getResult() );
    }

    @Test
    void testCheckInputWithInvalidBankCard() {
        final var resultType = inputChecker.checkInput("1234 5678 9012");
        assertNotNull(resultType);
        assertTrue(resultType instanceof NumberParserResult);
    }

    @Test
    void testCheckInputWithValidNumber() {
        final var parserResult = inputChecker.checkInput("12345");
        assertNotNull(parserResult);
        assertTrue(parserResult instanceof NumberParserResult);
    }

    @Test
    void testCheckInputWithInvalidNumber() {
        final var parserResult = inputChecker.checkInput("abcde");
        assertNotNull(parserResult);
        assertEquals(ResultType.EMPTY,parserResult.getType());
    }

    @Test
    void testCheckInputEmpty() {
        final ParserResult parserResult = inputChecker.checkInput("");
        assertNotNull(parserResult);
        assertEquals(ResultType.EMPTY,parserResult.getType());
    }

    @Test
    @DisplayName("Test check input with number `1 234 567 890.12` ")
    void testCheckInputWithNumber1() {
        final var resultType = inputChecker.checkInput("1 234 567 890.12");
        assertNotNull(resultType);
        assertEquals(new BigDecimal("1234567890.12"), resultType.getResult());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1 234 567 890,12/1234567890.12",
        "1.234.567.890,12/1234567890.12",
        "(1.234.567.890,12)/-1234567890.12"
    }, delimiter = '/')
    void testCheckInputWithNumber2(String arg, String expected) {
        final var resultType = inputChecker.checkInput(arg);
        assertNotNull(resultType);
        assertEquals(new BigDecimal(expected), resultType.getResult());
    }
}
