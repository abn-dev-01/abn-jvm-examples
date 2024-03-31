package pro.abnjava.jvm.number.converter.bankcard;

import java.util.Optional;

import pro.abnjava.jvm.number.converter.ResultType;

public class BankCardParserStandard implements BankCardParser {

    /**
     * Parses a string input to extract a valid bank card number.
     *
     * @param input The input string potentially containing a bank card number.
     *
     * @return A BigDecimal representing the bank card number, or null if a valid number is not found.
     */
    @Override
    public Optional<ResultType> toBankCardNumber(String input) {
        // Remove all non-digit characters
        String cleanedInput = input.replaceAll("\\D", "");

        // Check if the cleaned input has the correct length for a bank card number
        if (cleanedInput.length() == 16) {
            return Optional.of(new BankCardResult(cleanedInput));
        }

        // Return an EmptyResult if a valid bank card number is not found
        return Optional.empty();
    }
}
