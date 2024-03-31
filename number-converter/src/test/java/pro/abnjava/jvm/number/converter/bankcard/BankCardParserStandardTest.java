package pro.abnjava.jvm.number.converter.bankcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import pro.abnjava.jvm.number.converter.ResultType;

class BankCardParserStandardTest {

    private BankCardParser bankCardParser = new BankCardParserStandard();

    @Test
    void testToBankCardNumberWithValidInput() {
        final var bankCardNumber = bankCardParser.toBankCardNumber("1234 5678 9012 3456");
        assertTrue(bankCardNumber.isPresent());

        final ResultType actual = bankCardNumber.get();
        assertEquals(new BigDecimal("1234567890123456").toString(), actual.getNumber().toString());
    }

    @Test
    void testToBankCardNumberWithInvalidLengthShort() {
        final var bankCardNumber = bankCardParser.toBankCardNumber("1234 5678 9012");
        assertTrue(bankCardNumber.isEmpty());
    }

    @Test
    void testToBankCardNumberWithInvalidLengthLong() {
        assertTrue(bankCardParser.toBankCardNumber("12345678901234567890").isEmpty());
    }

    @Test
    void testToBankCardNumberWithNonDigitCharacters() {
        final Optional<ResultType> bankCardNumber = bankCardParser.toBankCardNumber("1234-5678-9012-3456");
        assertTrue(bankCardNumber.isPresent());
        final BankCardResult resultType = (BankCardResult) bankCardNumber.get();
        assertEquals("1234567890123456", resultType.getNumber().toString());
    }

    @Test
    void testToBankCardNumberWithExactLengthAndSpaces() {
        final Optional<ResultType> bankCardNumber = bankCardParser.toBankCardNumber("1234 5678 9012 3456");
        assertTrue(bankCardNumber.isPresent());
        final ResultType resultType = bankCardNumber.get();
        assertEquals("1234567890123456", resultType.getNumber().toString());
    }

    @Test
    void testToBankCardNumberWithExactLengthAndDashes() {
        final Optional<ResultType> bankCardNumber = bankCardParser.toBankCardNumber("1234-5678-9012-3456");
        assertTrue(bankCardNumber.isPresent());
        final ResultType resultType = bankCardNumber.get();
        assertEquals("1234567890123456", resultType.getNumber().toString());
    }
}
