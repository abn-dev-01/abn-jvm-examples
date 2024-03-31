package pro.abnjava.jvm.converter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private Utils(){}

    public static int countChars(String str, char charsToCount) {
        String regex = "[" + charsToCount + "]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
