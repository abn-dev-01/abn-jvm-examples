package pro.abnjava.jvm.number.converter.exeption;

public class NumberConverterException extends Exception{

    public NumberConverterException(String message) {
        super(message);
    }

    public NumberConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberConverterException(Throwable cause) {
        super(cause);
    }
}
