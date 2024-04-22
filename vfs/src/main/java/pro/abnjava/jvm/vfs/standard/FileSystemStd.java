package pro.abnjava.jvm.vfs.standard;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pro.abnjava.jvm.vfs.AbnFileSystem;
import pro.abnjava.jvm.vfs.AbnVirtualFileSystem;

@Log4j2
@RequiredArgsConstructor
@Service
public class FileSystemStd implements AbnFileSystem {

    private final AbnVirtualFileSystem vfs;

    @Override
    public void addFile(String path, byte[] data) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (vfs.getMap().containsKey(path)) {
            throw new RuntimeException("File already exists.");
        }
        String parentPath = path.substring(0, path.lastIndexOf('/'));
        if (!vfs.getMap().containsKey(parentPath) || !vfs.getMap().get(parentPath).isDirectory()) {
            throw new RuntimeException("No such directory.");
        }
        vfs.getMap().put(path, new FileSystemEntryStandard(path.substring(path.lastIndexOf('/') + 1), false));
        vfs.getMap().get(path).setData(data);
    }

    @Override
    public byte[] readFile(String path) {
        if (!vfs.getMap().containsKey(path) || vfs.getMap().get(path).isDirectory()) {
            throw new RuntimeException("No such file.");
        }
        return vfs.getMap().get(path).getData();
    }

    @Override
    public void deleteFile(String path) {
        if (!vfs.getMap().containsKey(path) || vfs.getMap().get(path).isDirectory()) {
            throw new RuntimeException("No such file.");
        }
        vfs.getMap().remove(path);
    }

    @Override
    public List<String> listFiles(String directoryPath) {
        if (!vfs.getMap().containsKey(directoryPath) || !vfs.getMap().get(directoryPath).isDirectory()) {
            throw new RuntimeException("No such directory.");
        }
        List<String> fileList = new ArrayList<>();
        for (String key : vfs.getMap().keySet()) {
            if (key.startsWith(directoryPath) && !key.equals(directoryPath)) {
                fileList.add(key);
            }
        }
        return fileList;
    }

    @Override
    public void createDirectory(String path) {
        // create MAP record
        if (vfs.getMap().containsKey(path)) {
            throw new RuntimeException("Directory already exists.");
        }
        try {
            vfs.getMap()
               .put(path, new FileSystemEntryStandard(
                   path.substring(path.lastIndexOf('/') + 1), true));
            // create bytes array in VFS
            

            //
        } catch (Exception e) {
            var removed = vfs.getMap().remove(path);
        } finally {
            LOG.info("createDirectory() - ", () -> path);
        }
    }
}
