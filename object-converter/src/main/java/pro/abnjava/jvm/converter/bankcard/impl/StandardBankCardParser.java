package pro.abnjava.jvm.converter.bankcard.impl;

import java.math.BigDecimal;
import java.util.Optional;

import pro.abnjava.jvm.converter.InputResult;
import pro.abnjava.jvm.converter.parser.ParserResult;
import pro.abnjava.jvm.converter.bankcard.BankCardParser;

/**
 * Standard Implementation of BankCardParser - that parses a string input to extract a valid bank card number.
 */
public class StandardBankCardParser implements BankCardParser {

    private boolean flag;
    public StandardBankCardParser() {
    }
    public StandardBankCardParser(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean returnInput() {
        return flag; // return empty when parsing failed.
    }

    /**
     * Parses a string input to extract a valid bank card number.
     *
     * @param input - The input string potentially containing a bank card number.
     *
     * @return A BigDecimal representing the bank card number, or null if a valid number is not found.
     */
    @Override
    public Optional<ParserResult<BigDecimal>> parse(String input) {
        // Remove all non-digit characters
        String cleanedInput = input.replaceAll("\\D", "");

        // Check if the cleaned input has the correct length for a bank card number
        if (cleanedInput.length() == 16) {
            return Optional.of(new BankCardResult(cleanedInput));
        }

        // Return an EmptyResult if a valid bank card number is not found
       return returnInput() ? Optional.of(new InputResult(input)) : Optional.empty();
    }
}
