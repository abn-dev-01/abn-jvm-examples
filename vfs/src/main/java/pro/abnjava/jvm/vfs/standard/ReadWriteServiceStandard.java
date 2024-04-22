package pro.abnjava.jvm.vfs.standard;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pro.abnjava.jvm.vfs.AbnReadWriteService;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReadWriteServiceStandard implements AbnReadWriteService {

    @Override
    public void write(FileChannel fileChannel, byte[] data, int offset, int length) throws IOException {
        // Writing data to a file
        ByteBuffer buf = ByteBuffer.wrap(data);
        buf.rewind();
        fileChannel.write(buf);
    }

    @Override
    public byte[] read(FileChannel fileChannel, int offset, int length) throws IOException {
        // Reading data from a file
        ByteBuffer readBuffer = ByteBuffer.allocate(length);
        fileChannel.position(offset); // Move to the beginning of the file
        fileChannel.read(readBuffer);
        readBuffer.flip();  // Switch from writing to reading mode
        byte[] array = new byte[readBuffer.remaining()];
        readBuffer.get(array);

        return array;
    }
}
