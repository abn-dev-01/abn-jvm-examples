package pro.abnjava.jvm.converter;

import java.util.Optional;

public interface ParserResult<T> {
    Optional<T> getResult();

    ResultType getType();
}
