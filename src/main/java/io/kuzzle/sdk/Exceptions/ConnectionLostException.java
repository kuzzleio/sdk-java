package io.kuzzle.sdk.Exceptions;

public class ConnectionLostException extends KuzzleException {
    public ConnectionLostException() {
        super("Connection lost", 500);
    }
}
