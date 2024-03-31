package pro.abnjava.jvm.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.bankcard.BankCardParser;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardParser;
import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberParser;

public class StandardInputChecker extends InputChecker {

    @Override
    protected BankCardValidator getBankCardValidator() {
        return new StandardBankCardValidator();
    }

    @Override
    protected NumberParser getNumberParser() {
        return new StandardNumberParser();
    }

    @Override
    protected Optional<ParserResult<BigDecimal>> parseBankCardNumber(String input) {
        BankCardParser bankCardParser = new StandardBankCardParser();
        return bankCardParser.parse(input);
    }
}
