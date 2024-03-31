package pro.abnjava.jvm.number.converter;

import java.math.BigDecimal;

public interface ParserResult {
    BigDecimal getNumber();
    NumberType getType();
}
