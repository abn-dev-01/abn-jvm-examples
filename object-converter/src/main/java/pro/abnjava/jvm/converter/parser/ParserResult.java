package pro.abnjava.jvm.converter.parser;

public interface ParserResult<T> {
    T getResult();

    ResultType getType();
}
