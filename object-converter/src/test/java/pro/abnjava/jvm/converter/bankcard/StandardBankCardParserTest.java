package pro.abnjava.jvm.converter.bankcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import pro.abnjava.jvm.converter.ParserResult;
import pro.abnjava.jvm.converter.bankcard.impl.BankCardResult;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardParser;

class StandardBankCardParserTest {

    private BankCardParser bankCardParser = new StandardBankCardParser();

    @Test
    void testToBankCardNumberWithValidInput() {
        final var bankCardNumber = bankCardParser.parse("1234 5678 9012 3456");
        assertTrue(bankCardNumber.isPresent());

        final var actual = bankCardNumber.get();
        assertEquals(new BigDecimal("1234567890123456").toString(), actual.getResult().toString());
    }

//    @Test
//    void testToBankCardNumberWithInvalidLengthShort() {
//        final var bankCardNumber = bankCardParser.getResult("1234 5678 9012");
//        assertTrue(bankCardNumber.isEmpty());
//    }
//
//    @Test
//    void testToBankCardNumberWithInvalidLengthLong() {
//        assertTrue(bankCardParser.getResult("12345678901234567890").isEmpty());
//    }
//
//    @Test
//    void testToBankCardNumberWithNonDigitCharacters() {
//        final Optional<ParserResult> bankCardNumber = bankCardParser.getResult("1234-5678-9012-3456");
//        assertTrue(bankCardNumber.isPresent());
//        final BankCardResult resultType = (BankCardResult) bankCardNumber.get();
//        assertEquals("1234567890123456", resultType.getResult().toString());
//    }
//
//    @Test
//    void testToBankCardNumberWithExactLengthAndSpaces() {
//        final Optional<ParserResult> bankCardNumber = bankCardParser.getResult("1234 5678 9012 3456");
//        assertTrue(bankCardNumber.isPresent());
//        final ParserResult parserResult = bankCardNumber.get();
//        assertEquals("1234567890123456", parserResult.getResult().toString());
//    }
//
//    @Test
//    void testToBankCardNumberWithExactLengthAndDashes() {
//        final Optional<ParserResult> bankCardNumber = bankCardParser.getResult("1234-5678-9012-3456");
//        assertTrue(bankCardNumber.isPresent());
//        final ParserResult parserResult = bankCardNumber.get();
//        assertEquals("1234567890123456", parserResult.getResult().toString());
//    }
}
