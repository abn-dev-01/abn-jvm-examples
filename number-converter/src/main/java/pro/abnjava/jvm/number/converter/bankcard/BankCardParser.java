package pro.abnjava.jvm.number.converter.bankcard;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.ParserResult;

public interface BankCardParser {


   Optional<ParserResult>  toBankCardNumber(String input);
}
