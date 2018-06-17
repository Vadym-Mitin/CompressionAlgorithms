package HuffmanCompression;

public class UnexpectedFileFormat extends Exception {
    public UnexpectedFileFormat() {
    }

    public UnexpectedFileFormat(String message) {
        super(message);
    }

    public UnexpectedFileFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedFileFormat(Throwable cause) {
        super(cause);
    }
}
