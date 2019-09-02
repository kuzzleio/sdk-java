package io.kuzzle.sdk.Exceptions;

/**
 * Root of all Kuzzle exceptions.
 */
public class KuzzleException extends Exception {

    /**
     * Kuzzle API error code.
     */
    protected int status;

    /** Initializes a new instance of the KuzzleException.
     * @param message Message.
     * @param status  Status.
     */
    protected KuzzleException(String message, int status) {
        super(message);
        this.status = status;
    }

    /**
     * @return The status code of the exception.
     */
    public int getStatus() {
        return status;
    }
}
