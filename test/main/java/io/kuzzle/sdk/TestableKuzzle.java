package main.java.io.kuzzle.sdk;

import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Response.Response;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

public class TestableKuzzle extends Kuzzle {

    public TestableKuzzle(AbstractProtocol networkProtocol) throws URISyntaxException, IllegalArgumentException {
        super(networkProtocol);
    }

    public TestableKuzzle(AbstractProtocol networkProtocol, KuzzleOptions options) throws IllegalArgumentException {
        super(networkProtocol, options);
    }

    public void setTokenExpiredEventListener(EventListener eventListener) {
        super.tokenExpiredEvent = eventListener;
    }

    public EventListener getTokenExpiredEventListener() {
        return super.tokenExpiredEvent;
    }

    public void onStateChanged(ProtocolState state) {
        super.onStateChanged(state);
    }

    public void onResponseReceived(String payload) {
        super.onResponseReceived(payload);
    }

    public ConcurrentHashMap<String, Task<Response>> getRequests() {
        return super.requests;
    }
}
