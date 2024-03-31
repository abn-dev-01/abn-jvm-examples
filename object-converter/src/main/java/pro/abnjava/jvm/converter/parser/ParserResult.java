package pro.abnjava.jvm.converter.parser;

import java.util.Optional;

import pro.abnjava.jvm.converter.ResultType;

public interface ParserResult<T> {
    Optional<T> getResult();

    ResultType getType();
}
