package pro.abnjava.jvm.converter.bankcard;

import pro.abnjava.jvm.converter.parser.ConverterValidator;

/**
 * API for Bank Card validation.
 */
public interface BankCardValidator extends ConverterValidator<String> {

    boolean validateAlgorithm(String input);
}
