package pro.abnjava.jvm.vfs.standard;

import java.io.RandomAccessFile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class VirtualFileSystemStandardTest {

    @Autowired
    private FileSystemStd fs;

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
            fs.createDirectory("/test");

            LOG.debug("create directory /test");
            //
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
