package pro.abnjava.jvm.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.numbers.NumberValidator;
import pro.abnjava.jvm.converter.parser.ParserResult;


public interface InputChecker<T> {

    ParserResult<T> checkInput(T input);

    NumberParser getNumberParser();
    NumberValidator getNumberValidator();

    BankCardValidator getBankCardValidator();

    Optional<ParserResult<BigDecimal>> parseBankCardNumber(T input);

}
