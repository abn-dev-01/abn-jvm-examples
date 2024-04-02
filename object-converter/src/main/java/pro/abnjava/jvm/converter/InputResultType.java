package pro.abnjava.jvm.converter;

import pro.abnjava.jvm.converter.parser.ResultType;

public class InputResultType implements ResultType {
    private String type = "INPUT";

    /**
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }
}
