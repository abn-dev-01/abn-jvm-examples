package pro.abnjava.jvm.converter;

import java.util.Optional;

import pro.abnjava.jvm.converter.parser.ParserResult;

public class InputResult <T> implements ParserResult<T> {

    private final T input;

    public InputResult(T arg) {
        this.input = arg;
    }

    /**
     * @return
     */
    @Override
    public Optional<T> getResult() {
        return Optional.ofNullable(this.input);
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return ResultType.INPUT;
    }
}
