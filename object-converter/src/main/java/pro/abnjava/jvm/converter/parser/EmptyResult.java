package pro.abnjava.jvm.converter.parser;

public class EmptyResult<T> implements ParserResult<T> {

    public static EmptyResult getInstance() { return new EmptyResult<>(); }

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
        return EmptyType.getInstance();
    }
}
