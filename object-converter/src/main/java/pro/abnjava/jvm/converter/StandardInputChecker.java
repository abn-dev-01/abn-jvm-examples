package pro.abnjava.jvm.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.bankcard.BankCardParser;
import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardParser;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberParser;
import pro.abnjava.jvm.converter.parser.ParserResult;

public class StandardInputChecker implements InputChecker<String> {

    protected BankCardValidator bankCardValidator;
    protected NumberParser numberParser;


    public StandardInputChecker() {
        this.bankCardValidator = getBankCardValidator();
        this.numberParser = getNumberParser();
    }

    @Override
    public BankCardValidator getBankCardValidator() {
        return new StandardBankCardValidator();
    }

    @Override
    public NumberParser getNumberParser() {
        return new StandardNumberParser();
    }

    @Override
    public Optional<ParserResult<BigDecimal>> parseBankCardNumber(String input) {
        BankCardParser bankCardParser = new StandardBankCardParser();
        return bankCardParser.parse(input);
    }

    /**
     * @param input The input string potentially containing a bank card number or a number.
     *
     * @return An Optional containing a NumberParserResult if the input is a valid number or a
     * BankCardResult, or an empty Optional.
     */
    @Override
    public Optional<ParserResult> checkInput(String input) {
        if (bankCardValidator.isBankCardNumber(input)) {
            return parseBankCardNumber(input);
        }

        return numberParser.parse(input);
    }
}
