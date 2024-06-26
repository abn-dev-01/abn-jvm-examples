package pro.abnjava.jvm.converter.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmptyResultTest {

    final EmptyResult emptyResult = new EmptyResult();

    @Test
    void getResult() {
        // exec
        assertThrows(UnsupportedOperationException.class, () -> emptyResult.getResult());
    }

    @Test
    void getType() {
        assertTrue(emptyResult.getType() instanceof EmptyType);
    }
}
