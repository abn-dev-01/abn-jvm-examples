package pro.abnjava.jvm.converter.bankcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        final Optional<BigDecimal> result = parserResult.getResult();
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("1234567890123456").toString(), result.get().toString());
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
        assertTrue(bankCardResult.getResult().isPresent());
        assertEquals("1234567890123456", bankCardResult.getResult().get().toString());
    }

    @Test
    void testToBankCardNumberWithExactLengthAndSpaces() {
        final var bankCardParserOpt = bankCardParser.parse("1234 5678 9012 3456");
        assertTrue(bankCardParserOpt.isPresent());

        final var parserResult = bankCardParserOpt.get();
        assertEquals("1234567890123456", parserResult.getResult().get().toString());
    }

    @Test
    void testToBankCardNumberWithExactLengthAndDashes() {
        final var bankCardParserOpt = bankCardParser.parse("1234-5678-9012-3456");
        assertTrue(bankCardParserOpt.isPresent());

        final var parserResult = bankCardParserOpt.get();
        assertEquals("1234567890123456", parserResult.getResult().get().toString());
    }
}
