package pro.abnjava.jvm.converter.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void countCharsTest() {
       var count= Utils.countChars("Hello. World. Good day! today.", '.');
        //import org.junit.jupiter.api.Assertions;
        Assertions.assertEquals(3, count, "Count of characters should be 3");
    }
}
