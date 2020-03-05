---
code: true
type: page
title: WebSocketOptions
description: KuzzleOptions class documentation
order: 110
---

# WebSocketOptions

This class represents the options usable with the Kuzzle class.

It can be used with the following methods:
 - [WebSocket.WebSocket](/sdk/java/3/protocols/websocket)

# Constructor

This class has a constructor and a constructor by copy.

## Getters and setters

### port

The port to use to connect.

```java
public int getPort();
public WebSocketOptions setPort(int port)
```

### ssl

If we use SSL connection.

```java
public boolean getSsl()
public WebSocketOptions setSsl(boolean ssl)
```

### connectionTimeout

The duration before the connection timeout.

```java
public int getConnectionTimeout()
public WebSocketOptions setConnectionTimeout(int connectionTimeout)
```

### autoReconnect

If the websocket auto reconnects.

```java
public boolean getAutoReconnect()
public KuzzleOptions setAutoReconnect(boolean autoReconnect)
```
