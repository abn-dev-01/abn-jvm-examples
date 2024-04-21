package pro.abnjava.jvm.vfs.standard;

import pro.abnjava.jvm.vfs.AbnFileSystemEntry;

public class FileSystemEntryStandard implements AbnFileSystemEntry {
    private String name;
    private boolean isDirectory;
    private byte[] data;

    public FileSystemEntryStandard(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.data = new byte[0];
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return isDirectory;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }
}
