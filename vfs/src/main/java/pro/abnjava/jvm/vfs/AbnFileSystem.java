package pro.abnjava.jvm.vfs;

import java.util.List;

public interface AbnFileSystem {
    void addFile(String path, byte[] data);

    byte[] readFile(String path);

    void deleteFile(String path);

    List<String> listFiles(String directoryPath);

    void createDirectory(String path);
}
