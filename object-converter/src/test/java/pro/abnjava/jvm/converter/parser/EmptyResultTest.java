package pro.abnjava.jvm.converter.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
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
        assertEquals(ResultType.EMPTY, emptyResult.getType());
    }
}
