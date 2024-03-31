package pro.abnjava.jvm.converter.bankcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardValidator;

class StandardBankCardValidatorTest {

    private BankCardValidator bankCardValidator = new StandardBankCardValidator();

    @Test
    void testIsBankCardNumberWithValidCard() {
        // Valid Visa test number
        assertTrue(bankCardValidator.isBankCardNumber("4532756279624064"));
    }

    @Test
    void testIsBankCardNumberWithInvalidCard() {
        // Invalid test number
        assertFalse(bankCardValidator.isBankCardNumber("1234567890123456"));
    }

    @Test
    void testIsBankCardNumberWithNonDigitCharacters() {
        // Valid Visa test number with dashes
        assertTrue(bankCardValidator.isBankCardNumber("4532-7562-7962-4064"));
    }

    @Test
    void testIsBankCardNumberWithInvalidLengthShort() {
        // Shorter than 16 digits
        assertFalse(bankCardValidator.isBankCardNumber("12345678901234"));
    }

    @Test
    void testIsBankCardNumberWithInvalidLengthLong() {
        // Longer than 16 digits
        assertFalse(bankCardValidator.isBankCardNumber("12345678901234567890"));
    }

    @Test
    void testIsBankCardNumberWithSpaces() {
        // Valid Visa test number with spaces
        assertTrue(bankCardValidator.isBankCardNumber("4532 7562 7962 4064"));
    }
}
