package io.kuzzle.test;

import io.kuzzle.runner.Kuzzle;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.CoreClasses.Responses.Response;

import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

public class TestableKuzzle<T> extends Kuzzle<T> {

    public TestableKuzzle(AbstractProtocol<T> networkProtocol) throws URISyntaxException, IllegalArgumentException {
        super(networkProtocol);
    }

    public TestableKuzzle(AbstractProtocol<T> networkProtocol, KuzzleOptions options) throws IllegalArgumentException {
        super(networkProtocol, options);
    }

    public void setTokenExpiredEventListener(EventListener eventListener) {
        super.tokenExpiredEvent = eventListener;
    }

    public EventListener getTokenExpiredEventListener() {
        return super.tokenExpiredEvent;
    }

    public void setUnhandledResponseEventListener(EventListener eventListener) {
        super.unhandledResponseEvent = eventListener;
    }

    public EventListener getUnhandledResponseEventListener() {
        return super.unhandledResponseEvent;
    }

    public void onStateChanged(ProtocolState state) {
        super.onStateChanged(state);
    }

    public void onResponseReceived(String payload) {
        super.onResponseReceived(payload);
    }

    public ConcurrentHashMap<String, Task<Response<T>>> getRequests() {
        return super.requests;
    }
}
