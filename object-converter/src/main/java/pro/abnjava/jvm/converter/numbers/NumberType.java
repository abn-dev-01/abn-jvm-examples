package pro.abnjava.jvm.converter.numbers;

import pro.abnjava.jvm.converter.parser.ResultType;

public class NumberType implements ResultType {
    String type = "NUMBER";

    /**
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }
}
