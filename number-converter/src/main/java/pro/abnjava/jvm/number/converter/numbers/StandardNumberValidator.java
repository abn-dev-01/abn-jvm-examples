package pro.abnjava.jvm.number.converter.numbers;


import org.apache.commons.lang3.StringUtils;

public class StandardNumberValidator implements NumberValidator {

    private static final String NUMBER_REGEX = "[\\d.\\s\\$\\€,\\-\\(\\)]*";


    /**
     * Validate if the arg is can be converted to a number.
     *
     * @param arg is the string to be validated.
     *
     * @return
     */
    @Override
    public boolean validate(String arg) {
        return StringUtils.isNotBlank(arg) && arg.matches(NUMBER_REGEX);
    }
}
