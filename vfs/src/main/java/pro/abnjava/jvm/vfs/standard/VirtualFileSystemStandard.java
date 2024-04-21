package pro.abnjava.jvm.vfs.standard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.log4j.Log4j2;
import pro.abnjava.jvm.vfs.AbnFileSystemEntry;
import pro.abnjava.jvm.vfs.AbnVirtualFileSystem;
import pro.abnjava.jvm.vfs.exception.VfsRuntimeException;

@Log4j2
public class VirtualFileSystemStandard implements AbnVirtualFileSystem {

    private static final String FILE_NAME = "vfs.dat";

    private Map<String, AbnFileSystemEntry> entries;

    public VirtualFileSystemStandard() {
        try {
            entries = new ConcurrentHashMap<>();
            initializeFileSystem();
        } catch (IOException e) {
            throw new VfsRuntimeException(e);
        } finally {
            LOG.info("VFS initialized.");
        }
    }

    private void initializeFileSystem() throws IOException {

        Path newFilePath = Paths.get(FILE_NAME);
        boolean fileExists = Files.exists(newFilePath);
        if (!fileExists) {
            Files.createFile(newFilePath);
            // Create root directory
            entries.put("/", new FileSystemEntryStandard("/", true));
        } else {
            LOG.info("Determined that VFS file already exists.");
        }


//        // Additional initialization, e.g., creating default directories or files
//        entries.put("/documents", new FileSystemEntry("documents", true));
//        entries.put("/images", new FileSystemEntry("images", true));
//
//        // Create a default file
//        String defaultFilePath = "/documents/readme.txt";
//        FileSystemEntry fileEntry = new FileSystemEntry("readme.txt", false);
//        fileEntry.setData("Welcome to your VFS!".getBytes());
//        entries.put(defaultFilePath, fileEntry);
    }

    @Override
    public RandomAccessFile getVfs() throws FileNotFoundException {

//            // Writing data to a file
//            String newData = "Data to write to file.";
//            ByteBuffer buf = ByteBuffer.wrap(newData.getBytes());
//            buf.rewind();
//            fileChannel.write(buf);
//
//            // Reading data from a file
//            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//            fileChannel.position(0); // Move to the beginning of the file
//            fileChannel.read(readBuffer);
//            readBuffer.flip();  // Switch from writing to reading mode
//            byte[] array = new byte[readBuffer.remaining()];
//            readBuffer.get(array);
//            System.out.println(new String(array));

        return new RandomAccessFile(FILE_NAME, "rw");
    }

    @Override
    public Map<String, AbnFileSystemEntry> getMap() {
        return this.entries;
    }
}
