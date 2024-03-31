package pro.abnjava.jvm.converter.bankcard;

import java.math.BigDecimal;

import pro.abnjava.jvm.converter.parser.ObjectParser;

/**
 * API for Bank Card implementation.
 * INPUT type is a String.
 * RESULT type is a BigDecimal.
 */
public interface BankCardParser extends ObjectParser<String, BigDecimal> {

}
