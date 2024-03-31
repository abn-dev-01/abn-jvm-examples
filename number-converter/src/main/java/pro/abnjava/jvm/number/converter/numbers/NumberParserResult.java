package pro.abnjava.jvm.number.converter.numbers;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.NumberType;
import pro.abnjava.jvm.number.converter.ParserResult;

public class NumberParserResult implements ParserResult {
    private final BigDecimal number;

    public NumberParserResult(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    /**
     * @return
     */
    @Override
    public NumberType getType() {
        return NumberType.NUMBER;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NumberParserResult{");
        sb.append("number=").append(number).append(",");
        sb.append("type=").append(getType());
        sb.append('}');
        return sb.toString();
    }
}
