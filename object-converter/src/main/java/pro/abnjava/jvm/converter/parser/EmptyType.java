package pro.abnjava.jvm.converter.parser;

public class EmptyType implements ResultType{
    public static final String TYPE = "EMPTY";

    public static ResultType getInstance() {
        return new EmptyType();
    }

    /**
     * @return
     */
    @Override
    public String getType() {
        return EmptyType.TYPE;
    }
}
