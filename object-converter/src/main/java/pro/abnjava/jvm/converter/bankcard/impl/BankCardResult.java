package pro.abnjava.jvm.converter.bankcard.impl;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.ResultType;
import pro.abnjava.jvm.converter.parser.ParserResult;

/**
 * Object representing a bank card number - the result of the parsing.
 */
public class BankCardResult implements ParserResult<BigDecimal> {
    private final BigDecimal bankCardNumber;

    public BankCardResult(String bankCardNumber) {
        this.bankCardNumber = new BigDecimal(bankCardNumber);
    }

    public Optional<BigDecimal> getResult() {
        return Optional.of(bankCardNumber);
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return ResultType.BANK_CARD;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankCardResult{");
        // TODO: Should we mask the number?
        sb.append("number=").append(bankCardNumber).append(",");
        sb.append("type=").append(getType());
        sb.append('}');
        return sb.toString();
    }
}
