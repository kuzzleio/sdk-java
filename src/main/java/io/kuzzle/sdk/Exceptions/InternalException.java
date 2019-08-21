package io.kuzzle.sdk.Exceptions;

import io.kuzzle.sdk.Kuzzle;

public class InternalException extends KuzzleException {

    public InternalException(String message, int status) {
        super(message, status);
    }

}
