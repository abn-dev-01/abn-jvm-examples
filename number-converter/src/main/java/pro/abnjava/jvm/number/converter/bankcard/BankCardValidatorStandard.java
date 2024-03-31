package pro.abnjava.jvm.number.converter.bankcard;

public class BankCardValidatorStandard implements BankCardValidator {

    @Override
    public boolean isBankCardNumber(String input) {
        // Remove all non-digit characters
        String digitsOnly = input.replaceAll("\\D", "");

        // Check if the input contains 16 digits
        if (!digitsOnly.matches("\\d{16}")) {
            return false;
        }

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
