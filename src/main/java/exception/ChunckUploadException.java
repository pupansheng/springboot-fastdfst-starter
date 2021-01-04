package exception;

/**
 * @author
 * @discription;
 * @time 2020/12/31 15:11
 */
public class ChunckUploadException extends Exception {

    public ChunckUploadException() {
    }

    public ChunckUploadException(String message) {
        super(message);
    }

    public ChunckUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChunckUploadException(Throwable cause) {
        super(cause);
    }

    public ChunckUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
