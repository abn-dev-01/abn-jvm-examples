package pro.abnjava.jvm.converter.standard;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.InputChecker;
import pro.abnjava.jvm.converter.bankcard.BankCardParser;
import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardParser;
import pro.abnjava.jvm.converter.bankcard.impl.StandardBankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.numbers.NumberType;
import pro.abnjava.jvm.converter.numbers.NumberValidator;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberParser;
import pro.abnjava.jvm.converter.numbers.impl.StandardNumberValidator;
import pro.abnjava.jvm.converter.parser.EmptyResult;
import pro.abnjava.jvm.converter.parser.ParserResult;

public class StandardInputChecker implements InputChecker<String> {

    protected BankCardValidator bankCardValidator;
    protected NumberParser numberParser;

    protected NumberValidator numberValidator;


    public StandardInputChecker() {
        this.bankCardValidator = getBankCardValidator();
        this.numberParser = getNumberParser();
        this.numberValidator = getNumberValidator();
    }

    @Override
    public BankCardValidator getBankCardValidator() {
        return new StandardBankCardValidator();
    }

    @Override
    public NumberParser getNumberParser() {
        return new StandardNumberParser();
    }

    /**
     * @return
     */
    @Override
    public NumberValidator getNumberValidator() {
        return new StandardNumberValidator();
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
    public ParserResult checkInput(String input) {
        if (bankCardValidator.validate(input)) {
            return parseBankCardNumber(input).orElse(new EmptyResult<>());
        }

        var numberParserResult = numberParser.parse(input).orElse(new EmptyResult<>());
        if (numberParserResult.getType() instanceof NumberType) {
            return numberParserResult;
        }

        return new EmptyResult<Void>();
    }
}
