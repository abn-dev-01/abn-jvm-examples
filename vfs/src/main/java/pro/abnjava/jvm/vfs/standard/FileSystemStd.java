package pro.abnjava.jvm.vfs.standard;

import java.util.ArrayList;
import java.util.List;

import pro.abnjava.jvm.vfs.AbnFileSystem;

public class FileSystemStd implements AbnFileSystem {

    public FileSystemStd() {}

    @Override
    public void addFile(String path, byte[] data) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (entries.containsKey(path)) {
            throw new RuntimeException("File already exists.");
        }
        String parentPath = path.substring(0, path.lastIndexOf('/'));
        if (!entries.containsKey(parentPath) || !entries.get(parentPath).isDirectory()) {
            throw new RuntimeException("No such directory.");
        }
        entries.put(path, new FileSystemEntryStandard(path.substring(path.lastIndexOf('/') + 1), false));
        entries.get(path).setData(data);
    }

    @Override
    public byte[] readFile(String path) {
        if (!entries.containsKey(path) || entries.get(path).isDirectory()) {
            throw new RuntimeException("No such file.");
        }
        return entries.get(path).getData();
    }

    @Override
    public void deleteFile(String path) {
        if (!entries.containsKey(path) || entries.get(path).isDirectory()) {
            throw new RuntimeException("No such file.");
        }
        entries.remove(path);
    }

    @Override
    public List<String> listFiles(String directoryPath) {
        if (!entries.containsKey(directoryPath) || !entries.get(directoryPath).isDirectory()) {
            throw new RuntimeException("No such directory.");
        }
        List<String> fileList = new ArrayList<>();
        for (String key : entries.keySet()) {
            if (key.startsWith(directoryPath) && !key.equals(directoryPath)) {
                fileList.add(key);
            }
        }
        return fileList;
    }

    @Override
    public void createDirectory(String path) {
        if (entries.containsKey(path)) {
            throw new RuntimeException("Directory already exists.");
        }
        entries.put(path, new FileSystemEntryStandard(path.substring(path.lastIndexOf('/') + 1), true));
    }
}
