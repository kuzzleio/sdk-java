package io.kuzzle.sdk.Exceptions;

public class NotConnectedException extends KuzzleException {
    public NotConnectedException() {
        super("Not connected.", 500);
    }
}
