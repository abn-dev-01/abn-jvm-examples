package pro.abnjava.jvm.vfs;

import java.io.IOException;
import java.nio.channels.FileChannel;

public interface AbnReadWriteService {
    void write(FileChannel fileChannel, byte[] data, int offset, int length) throws IOException;

    byte[] read(FileChannel fileChannel, int offset, int length) throws IOException;
}
