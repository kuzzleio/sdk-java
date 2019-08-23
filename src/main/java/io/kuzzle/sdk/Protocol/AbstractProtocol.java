package io.kuzzle.sdk.Protocol;

import com.google.gson.JsonObject;
import io.kuzzle.sdk.Events.EventListener;

import java.util.function.Consumer;

public abstract class AbstractProtocol {
    protected EventListener<ProtocolState> stateChanged;
    protected EventListener<String> messageReceived;

    public AbstractProtocol() {
        stateChanged = new EventListener<ProtocolState>();
        messageReceived = new EventListener<String>();
    }

    public abstract ProtocolState getState();

    public abstract void connect() throws Exception;
    public abstract void disconnect();

    public abstract void send(JsonObject payload);

    public boolean registerMessageEvent(Consumer<String> callback) {
        return messageReceived.register(callback);
    }
    public boolean unregisterMessageEvent(Consumer<String> callback) {
        return messageReceived.unregister(callback);
    }

    public boolean registerStateChange(Consumer<ProtocolState> callback) {
        return stateChanged.register(callback);
    }
    public boolean unregisterStateChange(Consumer<ProtocolState> callback) {
        return stateChanged.unregister(callback);
    }

    protected void dispatchStateChange(ProtocolState state) {
        stateChanged.trigger(state);
    }

    protected void dispatchMessageEvent(String message) {
        messageReceived.trigger(message);
    }
}
