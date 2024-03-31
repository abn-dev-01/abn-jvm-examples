package pro.abnjava.jvm.converter.numbers.impl;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.ResultType;
import pro.abnjava.jvm.converter.parser.ParserResult;

public class NumberParserResult implements ParserResult<BigDecimal> {
    private final BigDecimal number;

    public NumberParserResult(BigDecimal number) {
        this.number = number;
    }

    public Optional<BigDecimal> getResult() {
        return Optional.of(number);
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return ResultType.NUMBER;
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
