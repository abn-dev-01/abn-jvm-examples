package pro.abnjava.jvm.number.converter;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.number.converter.numbers.NumberParser;


public abstract class InputChecker {

    protected BankCardValidator bankCardValidator;
    protected NumberParser numberParser;

    protected InputChecker() {
        this.bankCardValidator = getBankCardValidator();
        this.numberParser = getNumberParser();
    }

    protected abstract BankCardValidator getBankCardValidator();

    protected abstract NumberParser getNumberParser();

    protected abstract Optional<ParserResult> parseBankCardNumber(String input);


    /**
     * @param input The input string potentially containing a bank card number or a number.
     *
     * @return An Optional containing a NumberParserResult if the input is a valid number or a
     * BankCardParserResult, or an empty Optional.
     */
    public Optional<ParserResult> checkInput(String input) {
        if (bankCardValidator.isBankCardNumber(input)) {
            return parseBankCardNumber(input);
        }

        return numberParser.toNumber(input);
    }
}
