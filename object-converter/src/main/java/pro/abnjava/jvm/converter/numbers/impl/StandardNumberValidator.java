package pro.abnjava.jvm.converter.numbers.impl;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import pro.abnjava.jvm.converter.numbers.NumberValidator;

@Log4j2
public class StandardNumberValidator implements NumberValidator {

//    private static final String NUMBER_REGEX = "[\\d.\\s\\$\\€,\\-\\(\\)]*";
//    private static final String NUMBER_REGEX =  "^(?!0\\d)(?!.*([.,])\\1)(?=.*\\d)[($]?[\\d., ]+[)]?[$€]?$\n";

    private static final String NUMBER_REGEX =
        "^(?:-)?(?:\\d{1,3}(?:[,.\\s]\\d{3})*(?:[.,]\\d+)?|\\(\\d{1,3}(?:[,.\\s]\\d{3})*(?:[.,]\\d+)?\\)|[$€]\\s?\\d{1,3}(?:[,.\\s]\\d{3})*(?:[.,]\\d+)?\\s?[$€]?|\\(\\s?\\d{1,3}(?:[,.\\s]\\d{3})*(?:[.,]\\d+)?\\s?[$€]?\\))$";

    // only start validation!
    private static final String REGEX_START_END =
        "^(?:\\$\\(|\\(\\$|\\(|\\$)(?=[^$]*\\$[^$]*$).*(?:\\$\\)|\\$\\)|\\)\\$)$";

    // validate start
    private static final String REGEX_START =
        "^(?:(([-]?)(\\$\\d|\\$\\s|\\$\\s\\d|\\(\\s\\$|\\d))|(\\(\\s\\d|\\$\\(|\\$\\s\\(|\\(\\$\\d|\\(\\$\\s\\d|\\(\\d))?([\\s\\d\\,\\.\\-\\)]*)$";
    private static final String REGEX_END =
        "^([\\s\\d\\,\\.\\-\\(]*)(?:\\d[\\s]?[\\s\\)]?\\$|\\d[\\s]?[\\$]?[\\s]?\\))?$";
    private static final String REGEX_COUNT_BORDERS = "";

    /**
     * Validate if the arg is valid and can be converted to a number.
     *
     * @param arg is the string to be validated.
     *
     * @return
     */
    @Override
    public boolean validate(String arg) {
        // validate step by step
        // sign $ can be only one time at the beginning or the end.
        boolean valid = StringUtils.isNotBlank(arg);
        valid &= arg.matches(REGEX_START) || arg.matches(REGEX_END);
        if (valid) {
            arg.replaceAll(REGEX_REPLACE, "");
        }
        return valid;
    }

    public boolean validateStart(String arg) {
        return arg.matches(REGEX_START);
    }

    public boolean validateEnd(String arg) {
        return arg.matches(REGEX_END);
    }
    public boolean validateCountBordersStartEnd(String arg) {
        return arg.matches(REGEX_END);
    }
}
