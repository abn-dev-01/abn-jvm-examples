package pro.abnjava.jvm.vfs;

public interface AbnFileSystemEntry {
    boolean isDirectory();

    byte[] getData();

    void setData(byte[] data);
}
