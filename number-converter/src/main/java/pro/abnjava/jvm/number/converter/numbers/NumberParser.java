package pro.abnjava.jvm.number.converter.numbers;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.ParserResult;

public interface NumberParser {

    Optional<ParserResult> toNumber(String input);

    NumberValidator getValidator();

}
