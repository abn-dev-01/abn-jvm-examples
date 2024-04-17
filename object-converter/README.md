# Object Converter library

Object converter library is a powerful and flexible API library designed to simplify your development process.
It offers API for converting an input object to a `ResultType` object.

* This API Library contains classes for parsing Objects to a `ResultType`.
* you can use a Standard implementation provided by this library
* Or create your own implementation by adding new result objects, parsers, etc. (See examples below.)

# Technologies

| Name      | Details    |
| ----      | ------      |
| Language  | Java, JDK 17 | 
| Builder | Gradle |


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

4. Examples:
   .....

    (1,. 23)
    ( 1,. 23 )
    $( 1., 23 )
    ( 123 )$
    ($111)
    ($111 000)
    1234$
    1234$-
    12,.34$-
    -1234$
    -1234
    1234
    1234 444
    1212 122 233 333
    -111 234
    -111.234
    -111,234,
    -111,234,000
    -$321
    $321-
    1
    123
    123456
