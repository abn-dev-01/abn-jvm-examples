package pro.abnjava.jvm.number.converter;

import java.util.Optional;

public class HowToUseDemo {

    public static void main(String[] args) {
        InputChecker checker = new StandardInputChecker();
        String input = "5590 9999 0000 9999";
        Optional<ResultType> result = checker.checkInput(input);

        result.ifPresentOrElse(
            r -> System.out.println("Valid input processed. Result: " + r),
            () -> System.out.println("Invalid input.")
        );
    }

}