package pro.abnjava.jvm.number.converter;

import java.math.BigDecimal;

public interface NumberParser {
    BigDecimal toNumber(String input);
}
