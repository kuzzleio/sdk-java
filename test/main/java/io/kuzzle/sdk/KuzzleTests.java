package main.java.io.kuzzle.sdk;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.kuzzle.sdk.CoreClasses.Task;
import io.kuzzle.sdk.Events.EventListener;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.CoreClasses.Response.ErrorResponse;
import io.kuzzle.sdk.CoreClasses.Response.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class KuzzleTests {
    private AbstractProtocol networkProtocol = mock(WebSocket.class);
    private EventListener tokenExpiredEventListener = mock(EventListener.class);
    private EventListener<Response>
            unhandledResponseEventListener = mock(EventListener.class);
    private TestableKuzzle kuzzle;

    @Before
    public void setup() throws URISyntaxException {
        kuzzle = new TestableKuzzle(networkProtocol);
        kuzzle.setTokenExpiredEventListener(tokenExpiredEventListener);
        kuzzle.setUnhandledResponseEventListener(unhandledResponseEventListener);
    }

    @Test
    public void connect() throws Exception {
        kuzzle.connect();
        verify(networkProtocol, times(1)).connect();
    }

    @Test
    public void disconnect() throws Exception {
        kuzzle.disconnect();
        verify(networkProtocol, times(1)).disconnect();
    }


    @Test
    public void registerTokenExpiredEvent() throws Exception {
        kuzzle.registerTokenExpiredEvent(() -> {});
        verify(tokenExpiredEventListener,
               times(1)
        ).register(any(Runnable.class));
    }

    @Test
    public void unregisterTokenExpiredEvent() throws Exception {
        kuzzle.unregisterTokenExpiredEvent(() -> {});
        verify(
                tokenExpiredEventListener,
                times(1)
        ).unregister(any(Runnable.class));
    }

    @Test
    public void onStateChanged() {
        ConcurrentHashMap<String, Task<Response>>
                requests = kuzzle.getRequests();

        Task<Response> response = new Task<Response>();
        requests.put("foobar", response);
        kuzzle.onStateChanged(ProtocolState.CLOSE);
        assertEquals(0, requests.size());
        assertTrue(response.isCompletedExceptionally());
    }

    @Test(expected = NotConnectedException.class)
    public void queryShouldThrowWhenNotConnected()
            throws NotConnectedException, InternalException {
        when(networkProtocol.getState())
            .thenAnswer(
                (Answer<ProtocolState>) invocation -> ProtocolState.CLOSE
            );

        kuzzle.query(new JsonObject());
    }

    @Test
    public void querySuccess() throws NotConnectedException, InternalException {
        when(networkProtocol.getState())
            .thenAnswer(
                (Answer<ProtocolState>) invocation -> ProtocolState.OPEN
            );

        CompletableFuture<Response> response = kuzzle.query(new JsonObject());
        assertNotNull(response);
        verify(
                networkProtocol,
                times(1)
        ).send(any(JsonObject.class));
    }

    @Test(expected = InternalException.class)
    public void queryShouldThrowWhenVolatileIsNotJsonObject()
            throws NotConnectedException, InternalException {
        when(networkProtocol.getState())
            .thenAnswer(
                (Answer<ProtocolState>) invocation -> ProtocolState.OPEN
            );

        JsonObject payload = new JsonObject();
        payload.addProperty("volatile", "foobar");

        kuzzle.query(payload);
    }

    @Test
    public void onResponseReceivedAndTokenIsExpired() {
        ConcurrentHashMap<String, Task<Response>>
                requests = kuzzle.getRequests();

        Response response = new Response();
        response.error = new ErrorResponse();
        response.error.message = "Token expired";
        response.error.status = 42;
        response.room = "room-id";

        Task<Response> task = new Task<Response>();

        requests.put("room-id", task);

        kuzzle.onResponseReceived(new Gson().toJson(response));

        verify(
                tokenExpiredEventListener,
                times(1)
        ).trigger();
    }

    @Test
    public void onResponseReceivedAndResponseIsUnhandled() {
        ConcurrentHashMap<String, Task<Response>>
                requests = kuzzle.getRequests();

        AtomicBoolean success = new AtomicBoolean(false);
        Response response = new Response();
        response.requestId = "foobar";

        Task<Response> task = new Task<Response>();

        requests.put("request-id", task);

        kuzzle.onResponseReceived(new Gson().toJson(response));

        verify(
                unhandledResponseEventListener,
                times(1)
        ).trigger(any(Response.class));
    }

}
