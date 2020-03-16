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
public WebSocketOptions setAutoReconnect(boolean autoReconnect)
```

### reconnectionDelay

Time between each reconnection attempt.

```java
public long getReconnectionDelay();
public WebSocketOptions setReconnectionDelay(long reconnectionDelay);
```

### reconnectionRetries

Number of attempts to try and reconnect. -1 for infinite attempts until the connection is established again.

```java
public long getReconnectionRetries();
public WebSocketOptions setReconnectionRetries(long reconnectionRetries);
```
