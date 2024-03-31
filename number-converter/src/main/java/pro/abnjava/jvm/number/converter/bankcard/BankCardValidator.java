package pro.abnjava.jvm.number.converter.bankcard;

public interface BankCardValidator {

    boolean isBankCardNumber(String input);
    boolean validateAlgorithm(String input);
}
