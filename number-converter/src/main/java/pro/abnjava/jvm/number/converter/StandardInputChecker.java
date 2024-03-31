package pro.abnjava.jvm.number.converter;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.bankcard.BankCardParser;
import pro.abnjava.jvm.number.converter.bankcard.BankCardParserStandard;
import pro.abnjava.jvm.number.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.number.converter.bankcard.BankCardValidatorStandard;
import pro.abnjava.jvm.number.converter.numbers.NumberParser;
import pro.abnjava.jvm.number.converter.numbers.NumberParserStandard;

public class StandardInputChecker extends InputChecker {

    @Override
    protected BankCardValidator getBankCardValidator() {
        return new BankCardValidatorStandard();
    }

    @Override
    protected NumberParser getNumberParser() {
        return new NumberParserStandard();
    }

    @Override
    protected Optional<ResultType> parseBankCardNumber(String input) {
        BankCardParser bankCardParser = new BankCardParserStandard();
        return bankCardParser.toBankCardNumber(input);
    }
}
