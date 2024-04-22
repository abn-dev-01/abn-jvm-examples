package pro.abnjava.jvm.vfs.standard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pro.abnjava.jvm.vfs.AbnFileSystemEntry;

@Log4j2
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class FileSystemEntryStandard implements AbnFileSystemEntry {
    private String name;
    private boolean isDirectory;
    private byte[] data;
    private int size = 0;
    private int offset = -1;

    public FileSystemEntryStandard(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.data = new byte[0];
    }
}
