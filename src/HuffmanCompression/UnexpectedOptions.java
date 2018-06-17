package HuffmanCompression;

public class UnexpectedOptions extends Exception {
    public UnexpectedOptions() {
    }

    public UnexpectedOptions(String message) {
        super(message);
    }

    public UnexpectedOptions(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedOptions(Throwable cause) {
        super(cause);
    }
}
