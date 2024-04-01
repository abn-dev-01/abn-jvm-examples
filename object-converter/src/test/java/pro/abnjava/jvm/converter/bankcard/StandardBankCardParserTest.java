package pro.abnjava.jvm.converter.bankcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardParser;

class StandardBankCardParserTest {

    private BankCardParser bankCardParser = new StandardBankCardParser(false);

    @Test
    void testToBankCardNumberWithValidInput() {
        final var bankCardNumberOpt = bankCardParser.parse("1234 5678 9012 3456");
        assertTrue(bankCardNumberOpt.isPresent());

        final var parserResult = bankCardNumberOpt.get();
        assertNotNull(parserResult);
        assertEquals(new BigDecimal("1234567890123456"), parserResult.getResult());
    }

    @Test
    void testToBankCardNumberWithInvalidLengthShort() {
        final var bankCardNumber = bankCardParser.parse("1234 5678 9012");
        assertTrue(bankCardNumber.isEmpty());
    }

    @Test
    void testToBankCardNumberWithInvalidLengthLong() {
        assertTrue(bankCardParser.parse("12345678901234567890").isEmpty());
    }

    @Test
    void testToBankCardNumberWithNonDigitCharacters() {
        final var bankCardParserOpt = bankCardParser.parse("1234-5678-9012-3456");
        assertTrue(bankCardParserOpt.isPresent());

        final var bankCardResult =  bankCardParserOpt.get();
        final BigDecimal result = bankCardResult.getResult();
        assertNotNull(result);
        assertEquals(new BigDecimal("1234567890123456"), result);
    }

    @Test
    void testToBankCardNumberWithExactLengthAndSpaces() {
        final var bankCardParserOpt = bankCardParser.parse("1234 5678 9012 3456");
        assertTrue(bankCardParserOpt.isPresent());

        final var parserResult = bankCardParserOpt.get();
        final BigDecimal result = parserResult.getResult();
        assertEquals(new BigDecimal("1234567890123456"), result);
    }

    @Test
    void testToBankCardNumberWithExactLengthAndDashes() {
        final var bankCardParserOpt = bankCardParser.parse("1234-5678-9012-3456");
        assertTrue(bankCardParserOpt.isPresent());

        final var parserResult = bankCardParserOpt.get();
        final BigDecimal result = parserResult.getResult();
        assertEquals(new BigDecimal("1234567890123456"), result);
    }
}
