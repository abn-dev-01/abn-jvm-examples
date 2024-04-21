# ABN Virtual Filesystem (ABN-VFS)

This is a virtual filesystem from ABN-DEV-01.

## The general IDEA:

- There is a file. It is the VFS-OS. File changes the size by adding, deleting or updating files.
- You can add, delete, read files, directories in ABN-VFS.

### Notes 

    - Replacing the hierarchical tree structure of FileSystemEntry objects with a HashMap to manage 
      the file system entries can simplify certain operations and improve lookup times. 
      In a hashmap-based approach, each file and directory could be accessed directly via its path, 
      which acts as a key, making operations like lookup, insert, and delete more efficient in terms 
      of time complexity.


### High-Level Concept:

1. **Key-Value Store**: Use the full path as the key and FileSystemEntry as the value. This allows direct access to any file or directory without needing to traverse a tree.
2. **Efficient Operations**: Operations like renaming, moving, or deleting files can be done more efficiently because locating a file doesn't require traversing from the root.
3. **Simplicity in Implementation**: The structure and management of directories and files become straightforward at the cost of potentially more complex operations for managing directories and their contents.
