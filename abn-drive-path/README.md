# README file before using

    Programming Language: Kotlin

LongToHexConverter - is util for preparing path within your Operation System some file.

A lot of file inside a dir will have negative performance during reading operations. This uitl helps you to keep in a
dir only 0xFF (255) files + folders.

## Conditions

- Positive numbers only and zero.
- Minimum number is 0.
- Maximum number is Long.MAX >> `7F-FF-FF-FF-FF-FF-FF-FF`

## 1st case:

- you have a database, table with ID of files properties ( name, size, path etc.) but you prefer to keep all files not
  in
  database but in OS.
- A records in `table.id = 1` will have path `00/00/00/00/00/00/00/01` - it is a directory path.
- Then you can use let's say UUID for unique name of your file:
  `00/00/00/00/00/00/00/01/775bb169-1b55-405f-bc59-12ca34fbffd2.file`
- Just add utils for working with OS folders and files.
