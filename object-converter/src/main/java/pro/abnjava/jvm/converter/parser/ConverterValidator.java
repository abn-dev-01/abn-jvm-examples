package pro.abnjava.jvm.converter.parser;

public interface ConverterValidator<T> {
    boolean validate(T input);
}
