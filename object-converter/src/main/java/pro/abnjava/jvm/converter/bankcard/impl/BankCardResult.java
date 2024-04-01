package pro.abnjava.jvm.converter.bankcard.impl;

import java.math.BigDecimal;

import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.parser.ResultType;

/**
 * Object representing a bank card number - the result of the parsing.
 */
public class BankCardResult implements ParserResult<BigDecimal> {
    private final BigDecimal bankCardNumber;

    public BankCardResult(String bankCardNumber) {
        this.bankCardNumber = new BigDecimal(bankCardNumber);
    }

    public BigDecimal getResult() {
        return bankCardNumber;
    }

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
