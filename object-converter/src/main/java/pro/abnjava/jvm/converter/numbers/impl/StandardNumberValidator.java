package pro.abnjava.jvm.converter.numbers.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import pro.abnjava.jvm.converter.numbers.NumberValidator;

@Log4j2
public class StandardNumberValidator implements NumberValidator {

    // validate start
    private static final String REGEX_START =
        "^(?:((-?)(\\$\\d|\\$\\s|\\$\\s\\d|\\(\\s\\$|\\d))|(\\(\\s\\d|\\$\\(" +
            "|\\$\\s\\(|\\(\\$\\d|\\(\\$\\s\\d|\\(\\d))?([\\s\\d\\,\\.\\-\\)\\$]*)$";
    private static final String REGEX_END = // valid ending
        "^([\\d\\s\\.\\,\\(\\$\\-]*)\\d(?:\\d\\s?\\)|[\\)\\s]?\\$|[\\d\\s]\\$\\)|\\d|[\\d\\s\\$]\\-)?$";

    /*
        Valid numbers:
        ( 11111 )
        (11111)

        ( 11111 )$
        ( 11111 $)
        (11111$)
        (11111)$

        $( 11111 )
        ($11111)
        $(11111)
     */
    private static final String REGEX_BORDERS =
        "^(?:\\$?\\([\\d\\,\\.\\s]+\\)|\\([\\d\\.\\,\\$]+\\)\\$?|\\$?\\(\\s\\d+\\s\\)|\\(\\d+\\)\\$|\\(\\s\\d+\\s\\)\\$" +
            "|\\d[\\d\\,\\.\\s]*\\$\\-?|\\-?\\d[\\d\\,\\.\\s]*\\$?|\\-?\\$?\\d[\\d\\,\\.\\s]*|\\$?[\\d\\,\\.\\s]*\\-?" +
            "|\\(\\$[\\d\\,\\.\\s]*\\))$";

    private static final String REGEX_MIDDLE = "^(?:\\d{1,3}(?:[,.\\s]\\d{3})*(?:[.,]\\d+)?" +
        "|\\d{1,3}(?:[,.\\s]\\d{3})+(?:[.,]\\d+)?|(\\d{1,3}(?:([., ])(?:\\d{3}\\1)*\\d{3})?(?:[.,]\\d*)?)" +
        "|\\d+[,.]?\\d+)(?<! )$";
    private static final String REGEX_NEGATIVE_SIGN =
        "^(?:\\-[\\d\\,\\.\\s]+|[\\d\\,\\.\\s]+\\-|\\(\\$?[\\d\\,\\.\\s]+\\$?\\))$";

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
        boolean isNegative = false;
        valid &= arg.matches(REGEX_START) && arg.matches(REGEX_END);
        if (valid) {
            var cleanStr = arg;
            // determine negative sign
            isNegative = arg.matches(REGEX_NEGATIVE_SIGN);

            Pattern pattern = Pattern.compile(".*[)$(-].*");
            Matcher matcher = pattern.matcher(arg);
            if (matcher.matches()) {
                if (validateBordersStartEnd(arg)) {
                    cleanStr= arg.replace("(", "");
                    cleanStr= cleanStr.replace("($", "");
                    cleanStr= cleanStr.replace("$(", "");
                    cleanStr= cleanStr.replace(")", "");
                    cleanStr= cleanStr.replace(")$", "");
                    cleanStr= cleanStr.replace("$)", "");
                    cleanStr= cleanStr.replace("$", "");
                    cleanStr = cleanStr.replace("-", "");
                } else{
                    return false;
                }
            }
            valid &= cleanStr.matches(REGEX_MIDDLE);
        }
        return valid;
    }

    public boolean validateStart(String arg) {
        return arg.matches(REGEX_START);
    }

    public boolean validateEnd(String arg) {
        return arg.matches(REGEX_END);
    }
    public boolean validateBordersStartEnd(String arg) {
        return arg.matches(REGEX_BORDERS);
    }
}
