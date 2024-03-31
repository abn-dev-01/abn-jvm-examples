package pro.abnjava.jvm.number.converter.bankcard;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.ResultType;

public interface BankCardParser {


   Optional<ResultType>  toBankCardNumber(String input);
}
