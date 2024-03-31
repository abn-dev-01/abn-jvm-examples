package pro.abnjava.jvm.number.converter.numbers;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.ResultType;

public class NumberResult implements ResultType {
    private final BigDecimal number;

    public NumberResult(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }
}
