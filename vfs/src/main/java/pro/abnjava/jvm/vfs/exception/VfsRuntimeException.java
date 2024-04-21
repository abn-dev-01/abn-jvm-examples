package pro.abnjava.jvm.vfs.exception;

public class VfsRuntimeException extends RuntimeException {

    public VfsRuntimeException() {
        super();
    }

    public VfsRuntimeException(String message) {
        super(message);
    }

    public VfsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VfsRuntimeException(Throwable cause) {
        super(cause);
    }
}
