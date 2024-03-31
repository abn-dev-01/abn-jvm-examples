package pro.abnjava.jvm.converter;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.bankcard.BankCardValidator;
import pro.abnjava.jvm.converter.numbers.NumberParser;
import pro.abnjava.jvm.converter.parser.ParserResult;


public interface InputChecker<T> {

    Optional<ParserResult> checkInput(T input);

    NumberParser getNumberParser();

    BankCardValidator getBankCardValidator();

    Optional<ParserResult<BigDecimal>> parseBankCardNumber(T input);

}
