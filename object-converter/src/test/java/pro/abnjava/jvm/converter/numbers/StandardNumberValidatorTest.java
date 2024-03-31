package pro.abnjava.jvm.converter.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberValidator;

class StandardNumberValidatorTest {

    NumberValidator validator = new StandardNumberValidator();

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "999",
        "0.1111",
        "$ 123 456.1111",
        "($ 123 456)",
        "(123 456$)",
        "-123 456$",
    })
    void testVakidateTrue(String arg) {
        assertTrue(validator.validate(arg));
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
