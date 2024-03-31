package pro.abnjava.jvm.number.converter.bankcard;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.NumberType;
import pro.abnjava.jvm.number.converter.ParserResult;

public class BankCardParserResult implements ParserResult {
    private final BigDecimal bankCardNumber;

    public BankCardParserResult(String bankCardNumber) {
        this.bankCardNumber = new BigDecimal(bankCardNumber);
    }

    public BigDecimal getNumber() {
        return bankCardNumber;
    }

    /**
     * @return
     */
    @Override
    public NumberType getType() {
        return NumberType.BANK_CARD;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankCardParserResult{");
        // TODO: Should we mask the number?
        sb.append("number=").append(bankCardNumber).append(",");
        sb.append("type=").append(getType());
        sb.append('}');
        return sb.toString();
    }
}
