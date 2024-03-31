# Number converter library

Number converter is a powerful and flexible API library designed to simplify your development process. It offers
converting a string to a `ResultType` object.

## Standard implementation

This library includes a Standard implementation and parses input string:

- It receives an input string argument,
- a validation determines it can be a Number or Bank Card,
- parse input string
- then returns:
  - Empty if parsing failed,
  - `NumberResult` if there found a number
  - `BankCardResult` if determined a Bank Card.

### Standard Number parser

Standard Number parser determines a number in given argument in the following situations:

Argument can contains the following characters: `$`, `€`, spaces, commas, dots, minus, brackets().

Examples:
 
    1 234 ~-------------> 1234
    1 234.01 ~----------> 1234.01
    1 234 567.002 ~-----> 1234567.002
    
    1,234,567 ~---------> 1234567 
    1,234.56 ~----------> 1234.56
    1,234.567 ~---------> 1234.567
    -1,234.567 ~--------> 1234.567
    1,234.567- ~--------> 1234.567
    1,234.567 - ~-------> 1234.567

    (1 234 567) ~-------> -1234567
    ($ 12,345.99) ~-----> -12345.99
    (12,345.99$) ~------> -12345.99
    (12,345.99 $) ~-----> -12345.99

    Abcd ---------------> empty result
    123 ABC ------------> empty result


### Standard Bank Card parser

Standard bank card parser has simple verification and Lunh algorithm. 

Examples:



# Useful informations
 
    ! This is not implemented !

When using a thousand separator for the number 0.001002003004, it depends on the convention being used. 
Different regions use different characters as thousand separators, and the placement of the separator 
can vary based on the length of the fractional part. Here are some possible ways to represent 
this number with a thousand separator:

## 1. No separator for fractional part: 

Since the number has more than three digits after the decimal point, typically, no thousand separator is used in the fractional part.
  - `0.001002003004` (no thousand separator in the fractional part)

## 2. Using a comma as the thousand separator

(common in many English-speaking countries):
  - `0.001,002,003,004`

## 3. Using a period as the thousand separator

(common in many European countries):
  - `0.001.002.003.004`

## 4. Using a space as the thousand separator 

(used in some countries as a more universal option):
  - `0.001 002 003 004`

It's important to note that the use of thousand separators in the fractional part of a number is not standard practice in most contexts. 
Typically, a thousand separators are used to group digits in the integer part of a number to improve readability. For the fractional part, the digits are usually left ungrouped.
