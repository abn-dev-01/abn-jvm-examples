package pro.abnjava.jvm.converter.parser;

public class EmptyResult<T> implements ParserResult<T> {

    /**
     * @return
     */
    @Override
    public T getResult() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    @Override
    public ResultType getType() {
        return ResultType.EMPTY;
    }
}
