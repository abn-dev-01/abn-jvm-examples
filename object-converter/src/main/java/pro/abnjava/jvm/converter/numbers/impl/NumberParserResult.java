package pro.abnjava.jvm.converter.numbers.impl;

import java.math.BigDecimal;

import pro.abnjava.jvm.converter.numbers.NumberType;
import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.parser.ResultType;

public class NumberParserResult implements ParserResult<BigDecimal> {
    private final BigDecimal number;

    public NumberParserResult(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getResult() {
        return number;
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return new NumberType();
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
