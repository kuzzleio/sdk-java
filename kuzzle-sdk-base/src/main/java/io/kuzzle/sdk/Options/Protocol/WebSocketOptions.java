package io.kuzzle.sdk.Options.Protocol;

public class WebSocketOptions {

    private int port = 7512;
    private boolean ssl = false;
    private int connectionTimeout = -1;

    public WebSocketOptions() {}

    public WebSocketOptions(WebSocketOptions other) {
        this.port = other.port;
        this.ssl = other.ssl;
        this.connectionTimeout = other.connectionTimeout;
    }

    public int getPort() {
        return port;
    }

    public WebSocketOptions withPort(int port) {
        this.port =
                port >= 0
                ? port
                : 7512;
        return this;
    }

    public boolean isSsl() {
        return ssl;
    }

    public WebSocketOptions withSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public WebSocketOptions withConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout =
                connectionTimeout < 0
                ? -1
                : connectionTimeout;
        return this;
    }
}
