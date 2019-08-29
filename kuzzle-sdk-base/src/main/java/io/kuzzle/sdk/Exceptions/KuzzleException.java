package io.kuzzle.sdk.Exceptions;

public class KuzzleException extends Exception {

    protected int status;

    protected KuzzleException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
