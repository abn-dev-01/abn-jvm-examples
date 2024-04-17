package pro.abnjava.jvm.converter;


import lombok.extern.log4j.Log4j2;
import pro.abnjava.jvm.converter.standard.StandardInputChecker;

@Log4j2
public class HowToUseDemo {

    public static void main(String[] args) {
        InputChecker checker = new StandardInputChecker();
        String input = "5590 9999 0000 9999";
        var result = checker.checkInput(input);

        result.getResult();
    }

//    public static void main(String[] args) {
//        InputChecker checker = new StandardInputChecker();
//        String input = "4532 7562 7962 4064";
//        var result = checker.checkInput(input);
//
//        result.ifPresentOrElse(
//            r -> System.out.println("Valid input processed. Result: " + r),
//            () -> System.out.println("Invalid input.")
//        );
//    }

}
