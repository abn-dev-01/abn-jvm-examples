package pro.abnjava.jvm.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;


public abstract class InputChecker {

    protected BankCardValidator bankCardValidator;
    protected NumberParser numberParser;

    protected InputChecker() {
        this.bankCardValidator = getBankCardValidator();
        this.numberParser = getNumberParser();
    }

    protected abstract BankCardValidator getBankCardValidator();

    protected abstract NumberParser getNumberParser();

    protected abstract Optional<ParserResult<BigDecimal>> parseBankCardNumber(String input);


    /**
     * @param input The input string potentially containing a bank card number or a number.
     *
     * @return An Optional containing a NumberParserResult if the input is a valid number or a
     * BankCardResult, or an empty Optional.
     */
    public Optional<ParserResult<BigDecimal>> checkInput(String input) {
        if (bankCardValidator.isBankCardNumber(input)) {
            return parseBankCardNumber(input);
        }

        return numberParser.parse(input);
    }
}
