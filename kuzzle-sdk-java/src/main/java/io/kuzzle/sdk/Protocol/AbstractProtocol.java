package io.kuzzle.sdk.Protocol;

import io.kuzzle.sdk.Events.EventListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class AbstractProtocol {
    protected EventListener<ProtocolState> stateChanged;
    protected EventListener<String> messageReceived;

    public AbstractProtocol() {
        stateChanged = new EventListener<>();
        messageReceived = new EventListener<>();
    }

    /** Current connection state.
     * @return The state.
     */
    public abstract ProtocolState getState();

    /** connect this instance.
     * @throws Exception
     */
    public abstract void connect() throws Exception;

    /**
     * Disconnect this instance.
     */
    public abstract void disconnect();

    /** Send the specified payload to Kuzzle.
     * @param payload
     */
    public abstract void send(ConcurrentHashMap<String, Object> payload);


    /**
     * Register to the Response event
     */
    public boolean registerResponseEvent(Consumer<String> callback) {
        return messageReceived.register(callback);
    }

    /**
     * Unregister from the Response event
     */
    public boolean unregisterResponseEvent(Consumer<String> callback) {
        return messageReceived.unregister(callback);
    }

    /**
     * Register to the StateChange event
     */
    public boolean registerStateChangeEvent(Consumer<ProtocolState> callback) {
        return stateChanged.register(callback);
    }

    /**
     * Unregister from the StateChange event
     */
    public boolean unregisterStateChangeEvent(Consumer<ProtocolState> callback) {
        return stateChanged.unregister(callback);
    }


    /** Dispatch a state changed event.
     * @param state The ProtocolState.
     */
    protected void dispatchStateChangeEvent(ProtocolState state) {
        stateChanged.trigger(state);
    }

    /** Dispatch a message received from a Kuzzle server.
     * @param message Kuzzle API response.
     */
    protected void dispatchResponseEvent(String message) {
        messageReceived.trigger(message);
    }
}
