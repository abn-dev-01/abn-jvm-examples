package pro.abnjava.jvm.vfs;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Map;

/**
 * Virtual File System like a FAT file system.
 */
public interface AbnVirtualFileSystem {

    RandomAccessFile getVfs() throws FileNotFoundException;

    Map<String, AbnFileSystemEntry> getMap();
}
