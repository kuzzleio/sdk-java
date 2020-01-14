---
code: true
type: page
title: Constructor
description: Creates a new WebSocket protocol
order: 50
---

# Constructor

Initializes a new instance of the WebSocket class pointing to the Kuzzle server specified by the uri.

## Arguments

```java
public WebSocket(URI uri, WebSocketOptions options) throws URISyntaxException, IllegalArgumentException
```

<br/>

| Argument  | Type              | Description                  |
| --------- | ----------------- | ---------------------------- |
| `uri`    | <pre>Uri</pre> | URI pointing to a Kuzzle server |
| `options` | <pre>WebSocketOptions</pre> | Class which contains websocket options |

### uri

A Uri object pointing to a Kuzzle server.  

Use `wss://<host>:<ip>` to initiate a secure SSL connection.

## options

The options used to connect to the Kuzzle instance.
[WebSocketOptions](/sdk/java/3/core-classes/websocket-options)

## Return

A `WebSocket` protocol instance.

## Arguments

```java
public WebSocket(String host, WebSocketOptions options) throws URISyntaxException, IllegalArgumentException
```

<br/>

| Argument  | Type              | Description                  |
| --------- | ----------------- | ---------------------------- |
| `host`    | <pre>String</pre> | Host pointing to a Kuzzle server |
| `options` | <pre>WebSocketOptions</pre> | Class which contains websocket options |

### host

A host pointing to a Kuzzle server.  

Use `wss://<host>:<ip>` to initiate a secure SSL connection.

## options

The options used to connect to the Kuzzle instance.
[WebSocketOptions](/sdk/java/3/protocols/websocket/websocket-options)

## Return

A `WebSocket` protocol instance.

## Usage

<<< ./snippets/constructor.java
