package exception;

/**
 * @author
 * @discription;
 * @time 2020/12/31 15:11
 */
public class ChunckInitException extends Exception {

    public ChunckInitException() {
    }

    public ChunckInitException(String message) {
        super(message);
    }

    public ChunckInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChunckInitException(Throwable cause) {
        super(cause);
    }

    public ChunckInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
