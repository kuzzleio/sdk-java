---
code: true
type: page
title: constructor
description: This constructor creates a new WebSocket connection, using the specified options.
---

# constructor

This constructor creates a new WebSocket connection, using the specified options.

## Arguments

```java
public WebSocket(String host) throws URISyntaxException, IllegalArgumentException;

public WebSocket(
    String host,
    WebSocketOptions options
) throws URISyntaxException, IllegalArgumentException;
```

| Argument  | Type                        | Description                  |
|-----------|-----------------------------|------------------------------|
| `host`    | <pre>String</pre>           | Kuzzle server hostname or IP |
| `options` | <pre>WebSocketOptions</pre> | WebSocket connection options |

### options

| Argument            | Type<br>(default)               | Description                                         |
|---------------------|---------------------------------|-----------------------------------------------------|
| `port`              | <pre>int</pre><br>(`7512`)      | Kuzzle server port                                  |
| `ssl`               | <pre>boolean</pre><br>(`false`) | Use SSL to connect to Kuzzle server                 |
| `connectionTimeout` | <pre>int</pre><br>(`-1`)        | Connection timeout delay                            |
| `autoReconnect`     | <pre>boolean</pre><br>(`true`)  | Automatically reconnect to Kuzzle when disconnected |

## Return

A `WebSocket` protocol instance

## Usage

<<< ./snippets/constructor.java