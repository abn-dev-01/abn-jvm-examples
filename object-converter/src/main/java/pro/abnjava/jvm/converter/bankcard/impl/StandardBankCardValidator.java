package pro.abnjava.jvm.converter.bankcard.impl;

import pro.abnjava.jvm.converter.bankcard.BankCardValidator;

public class StandardBankCardValidator implements BankCardValidator {

    @Override
    public boolean isBankCardNumber(String input) {
        // Remove all non-digit characters
        String digitsOnly = input.replaceAll("\\D", "");

        // Check if the input contains 16 digits
        if (!digitsOnly.matches("\\d{16}")) {
            return false;
        }

        return this.validateAlgorithm(digitsOnly);
    }

    /**
     * The Luhn algorithm, also known as the "modulus 10" or "mod 10" algorithm, is a simple checksum formula
     * used to validate a variety of identification numbers, such as credit card numbers, IMEI numbers,
     * and National Provider Identifier numbers in the United States. It was created by Hans Peter Luhn,
     * an IBM scientist, in 1954.
     * <p>
     * The purpose of the algorithm is to detect any errors that might have been made in the transcription
     * of the number. It works by applying a simple formula to the digits of the number.
     *
     * @param digitsOnly The input string containing only digits
     *
     * @return
     */
    @Override
    public boolean validateAlgorithm(String digitsOnly) {
        // Implement the Luhn algorithm
        int sum = 0;
        boolean alternate = false;
        for (int i = digitsOnly.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(digitsOnly.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
