package pro.abnjava.jvm.number.converter.numbers;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.abnjava.jvm.number.converter.numbers.NumberParser;
import pro.abnjava.jvm.number.converter.numbers.NumberParserStandard;

class NumberParserStandardTest {

    private final NumberParser parser = new NumberParserStandard();

    @BeforeEach
    void setUp() {
    }

    @Test
    void toNumber() {
        Assertions.assertEquals(new BigDecimal("1234.56"), parser.toNumber("1,234.56"));
        Assertions.assertEquals(new BigDecimal("1234555.56"), parser.toNumber("1,234,555.56"));
        Assertions.assertEquals(new BigDecimal("1234.56"), parser.toNumber("1.234,56"));
        Assertions.assertEquals(new BigDecimal("1234666.56"), parser.toNumber("1.234.666,56"));
        Assertions.assertEquals(new BigDecimal("-1234.56"), parser.toNumber("(1,234.56)"));
        Assertions.assertEquals(new BigDecimal("-1234567.56"), parser.toNumber("(1,234,567.56)"));
    }


    @Test
    void toNumberUsdTest() {
        Assertions.assertEquals(new BigDecimal("1234.56"), parser.toNumber("$1,234.56"));
        Assertions.assertEquals(new BigDecimal("1234.56"), parser.toNumber("1,234.56 $"));
    }


    @Test
    void toNumberSpaceTest() {
        Assertions.assertEquals(new BigDecimal("-1234567.567"), parser.toNumber("-1 234 567.567"));
        Assertions.assertEquals(new BigDecimal("-1234"), parser.toNumber("(1,234)"));
        Assertions.assertEquals(new BigDecimal("-1234567"), parser.toNumber("(1,234,567)"));

        Assertions.assertEquals(new BigDecimal("-1234.56"), parser.toNumber("-1 234.56"));
        Assertions.assertEquals(new BigDecimal("-1234567.56"), parser.toNumber("- 1 234 567.56"));
        Assertions.assertEquals(new BigDecimal("-1234567.56"), parser.toNumber("1 234 567.56-"));
        Assertions.assertEquals(new BigDecimal("-1234567.56"), parser.toNumber("1 234 567.56 -"));
    }


}
