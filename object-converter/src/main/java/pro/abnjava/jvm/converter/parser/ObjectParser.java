package pro.abnjava.jvm.converter.parser;

import java.util.Optional;

/**
 * Abstract converter for your input and result types.
 *
 * @param <I>  is an input argument type (String, Integer, BigDecimal, ...)
 * @param <R> is a result type of parsing(String, Integer, BigDecimal, ...)
 */
public interface ObjectParser<I, R> {

    Optional<ParserResult<R>> parse(I argument);

    /*
     * When it returns true, the `parse()` method will return the input when parsing failed.
     * Otherwise, it will return Optional.empty.
     */
    boolean returnInput();
}
