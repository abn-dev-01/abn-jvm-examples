package pro.abnjava.jvm.number.converter.bankcard;

import java.math.BigDecimal;

import pro.abnjava.jvm.number.converter.ResultType;

public class BankCardResult implements ResultType {
    private final BigDecimal bankCardNumber;

    public BankCardResult(String bankCardNumber) {
        this.bankCardNumber = new BigDecimal(bankCardNumber);
    }

    public BigDecimal getNumber() {
        return bankCardNumber;
    }
}
