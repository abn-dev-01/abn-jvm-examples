package pro.abnjava.jvm.converter.bankcard;

import pro.abnjava.jvm.converter.parser.ResultType;

public class BankCardType implements ResultType {
    public static final String type = "BANK_CARD";

    public static ResultType getInstance() {return new BankCardType();}

    /**
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }
}
