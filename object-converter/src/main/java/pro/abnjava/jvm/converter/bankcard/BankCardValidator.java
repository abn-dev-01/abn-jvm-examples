package pro.abnjava.jvm.converter.bankcard;

/**
 * API for Bank Card validation.
 */
public interface BankCardValidator {

    boolean isBankCardNumber(String input);
    boolean validateAlgorithm(String input);
}
