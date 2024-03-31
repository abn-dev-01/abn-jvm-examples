package pro.abnjava.jvm.number.converter.numbers;

import java.math.BigDecimal;

public interface NumberParser {

    BigDecimal toNumber(String input);
}
