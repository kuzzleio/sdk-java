package io.kuzzle.sdk.Exceptions;

public class InternalException extends KuzzleException {

    public InternalException(String message, int status) {
        super(message, status);
    }

}
