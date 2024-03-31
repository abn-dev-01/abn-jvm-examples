package pro.abnjava.jvm.number.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.number.converter.bankcard.BankCardParser;
import pro.abnjava.jvm.number.converter.bankcard.BankCardParserStandard;
import pro.abnjava.jvm.number.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.number.converter.bankcard.BankCardValidatorStandard;
import pro.abnjava.jvm.number.converter.numbers.NumberParser;
import pro.abnjava.jvm.number.converter.numbers.NumberParserStandard;
import pro.abnjava.jvm.number.converter.numbers.NumberResult;

public class InputChecker {

    private final BankCardValidator bankCardValidator = new BankCardValidatorStandard();
    private final NumberParser numberParser = new NumberParserStandard() {};

    public Optional<ResultType> checkInput(String input) {
        if (bankCardValidator.isBankCardNumber(input)) {
            BankCardParser bankCardParser = new BankCardParserStandard();
            return bankCardParser.toBankCardNumber(input);
        }

        try {
            BigDecimal number = numberParser.toNumber(input);
            return Optional.of(new NumberResult(number));
        } catch (NumberFormatException e) {
            // Not a valid number
        }

        return Optional.empty();
    }
}
