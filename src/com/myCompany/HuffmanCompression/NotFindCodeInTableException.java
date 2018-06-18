package com.myCompany.HuffmanCompression;

public class NotFindCodeInTableException extends Exception {
    public NotFindCodeInTableException() {
    }

    public NotFindCodeInTableException(String message) {
        super(message);
    }

    public NotFindCodeInTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindCodeInTableException(Throwable cause) {
        super(cause);
    }
}
