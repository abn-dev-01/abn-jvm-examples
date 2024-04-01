package pro.abnjava.jvm.converter;

import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.parser.ResultType;

public class InputResult <T> implements ParserResult<T> {

    private final T input;

    public InputResult(T arg) {
        this.input = arg;
    }

    /**
     * @return
     */
    @Override
    public T getResult() {
        return this.input;
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return ResultType.INPUT;
    }
}
