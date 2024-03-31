package pro.abnjava.jvm.converter.parser;

import java.util.Optional;

/**
 * Abstract converter for your input and result types.
 *
 * @param <INPUT>  is an input argument type (String, Integer, BigDecimal, ...)
 * @param <RESULT> is a result type of parsing(String, Integer, BigDecimal, ...)
 */
public interface ObjectParser<INPUT, RESULT> {

    Optional<ParserResult<RESULT>> parse(INPUT argument);

    //ResultType getType();

    /*
     * When it returns true, the `parse()` method will return the input when parsing failed.
     * Otherwise, it will return Optional.empty.
     */
    boolean returnInput();
}
