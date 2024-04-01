# Object Converter library

Object converter library is a powerful and flexible API library designed to simplify your development process.
It offers API for converting an input object to a `ResultType` object.

* This API Library contains classes for parsing Object to a `ResultType`.
* And you can use a Standard implementations provided by this library
* Or create your own implementation adding a new Result Objects, parsers etc (see examples below).

-----------------------------------------

## Standard implementation

This library includes a Standard implementation and parses input string:

- It receives an input string argument,
- a validation determines it can be a Number or Bank Card,
- parse input string
- then returns:
  - `EmptyResult` if parsing failed,
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
Possible characters: 16 numbers, spaces or minus.

Examples:

    1111 1111 1111 1111
    1111-1111-1111-1111

# Example if implementation

1. Implement your version of Parser and Validator.

- your version of `ObjectParser`
- your version of `ConvertrValidator`. See at examples of `BankCardValidator` and `NumberValidator`

2. Implement `ParserRsult`

3. Extend `InputChecker` adding a new object. 
