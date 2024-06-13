package pro.abnjava.jvm.converter.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberValidator;

class StandardNumberValidatorTest {

    StandardNumberValidator validator = new StandardNumberValidator();

    @ParameterizedTest
    @DisplayName("Validate only begin of string, assumed that the rest part is valid, & hasnt invalid")
    @ValueSource(strings = {
        "1,234.6666$",
        "1",
        "$0",
        "$999",
        "$ 123456",
        "($ 123456)",
        "($123456)",
        "$(123)",
        "$ (123)",
        "$ (123123)",
        "-123456",
        "123456-",
    })
    void testValidateBeginStringOnly(String arg) {
        assertTrue(validator.validateStart(arg));
    }

    @ParameterizedTest
    @DisplayName("Validate only the END of string, assumed that the rest part is valid")
    @ValueSource(strings = {
        "1",
        "$1",
        "1$",
        "999",
        "-999",
        "999-",
        "999$",
        "999 $",
        "0.1111$",
        "0.22222",
        "123 456.1111$",
        "(123456$)",
        "(123456)$",
        "(123 456)",
        "111$-"
    })
    void testValidateEndStringOnlyExpectTrue(String arg) {
        assertTrue(validator.validateEnd(arg));
    }

    @ParameterizedTest
    @DisplayName("check invalid only the END of string, assumed that the rest part is valid")
    @ValueSource(strings = {
        "1  $",
        "1. $",
        "999.$",
        "011,$",
        "123.333.",
        "22(",
        "(111.$)",
        "(111.$)-"
    })
    void testValidateEndStringOnlyExpectFalse(String arg) {
        assertFalse(validator.validateEnd(arg));
    }

    @ParameterizedTest
    @DisplayName("Validate only the Begin and END of string, assumed that the rest part is valid")
    @ValueSource(strings = {
        "($ 123456)",
        "($123456)",
        "($123 456)",
        "$123456",
        "-$123456",
        "-123456",
        "$123456-",
        "123456-",
        "$123 456",
    })
    void testValidateBordersShouldBeTrue(String arg) {
        assertTrue(validator.validateBordersStartEnd(arg));
    }

    @ParameterizedTest
    @DisplayName("Validate only the Begin and END of string, assumed that the rest part is valid")
    @ValueSource(strings = {
        "($ 123456$)",
        "($ 123456 $)",
        "(123 456$ )",
        "$(123 456)$",
        ")123 456 )$",
        "(123 456 ($",
        "(123 456$",
    })
    void testValidateBordersShouldBeFalse(String arg) {
        assertFalse(validator.validateBordersStartEnd(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "a",
        "abc",
        "[0.1111]",
        "1.234.",
        "1234 234", // invalid format  - is not a number
    })
    void testValidateIsSupposedToBeFalse(String arg) {
        assertFalse(validator.validate(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "-1234234",
        "1234234-",
        "11234,333",
        "1.234",
        "1234234",
        "123 423 400",
        "123,423,400",
        "123.423.400",
        "123,423.400",
        "123 423.400",
        "123 423,400",
        "0,400",
        "0,4",
        "0,40000004",
    })
    void testValidateIsSupposedToBeTrue(String arg) {
        assertTrue(validator.validate(arg));
    }
}
