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


public abstract class InputChecker {

    protected BankCardValidator bankCardValidator;
    protected NumberParser numberParser;

    public InputChecker() {
        this.bankCardValidator = getBankCardValidator();
        this.numberParser = getNumberParser();
    }

    protected abstract BankCardValidator getBankCardValidator();

    protected abstract NumberParser getNumberParser();

    protected abstract Optional<ResultType> parseBankCardNumber(String input);


    /**
     * @param input The input string potentially containing a bank card number or a number.
     *
     * @return An Optional containing a NumberResult if the input is a valid number or a
     * BankCardResult if the input is a valid bank card number.
     */
    public Optional<ResultType> checkInput(String input) {
        if (bankCardValidator.isBankCardNumber(input)) {
            return parseBankCardNumber(input);
        }

        try {
            BigDecimal number = numberParser.toNumber(input);
            return Optional.of(new NumberResult(number));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


//    public Optional<ResultType> checkInput(String input) {
//        if (bankCardValidator.isBankCardNumber(input)) {
//            BankCardParser bankCardParser = new BankCardParserStandard();
//            return bankCardParser.toBankCardNumber(input);
//        }
//
//        try {
//            BigDecimal number = numberParser.toNumber(input);
//            return Optional.of(new NumberResult(number));
//        } catch (NumberFormatException e) {
//            // Not a valid number
//        }
//
//        return Optional.empty();
//    }
}
