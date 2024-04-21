package pro.abnjava.jvm.vfs.standard;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VirtualFileSystemStandardTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void VfsInitialisedTest() {
        var vfs = new VirtualFileSystemStandard();
        try (RandomAccessFile vfs1 = vfs.getVfs()) {
            Assertions.assertNotNull(vfs1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void vfsReadTest() {
        var vfsStd = new VirtualFileSystemStandard();
        try (RandomAccessFile vfs = vfsStd.getVfs()) {
            var channel = vfs.getChannel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
