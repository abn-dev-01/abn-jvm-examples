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
    })
    void testValidateBeginStringOnly(String arg) {
        assertTrue(validator.validateStart(arg));
    }

    @ParameterizedTest
    @DisplayName("Validate only the END of string, assumed that the rest part is valid")
    @ValueSource(strings = {
        "1",
        "999",
        "999$",
        "0.1111$",
        "0.22222",
        "123 456.1111$",
        "(123456$)",
        "(123456)$",
        "(123 456)",
    })
    void testValidateEndStringOnly(String arg) {
        assertTrue(validator.validateEnd(arg));
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
    void testValidateShouldBeFalse(String arg) {
        assertFalse(validator.validate(arg));
    }
    @ParameterizedTest
    @ValueSource(strings = {
        "a",
        "abc",
        "[0.1111]",
    })
    void testVakidateFalse(String arg) {
        assertFalse(validator.validate(arg));
    }
}
