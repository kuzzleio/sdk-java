package io.kuzzle.sdk.Exceptions;

/**
 * Passed to async tasks when an API request returns an error.
 */
public class InternalException extends KuzzleException {

    /**
     * Initializes a new instance of the InternalException
     */
    public InternalException(String message, int status) {
        super(message, status);
    }

}
