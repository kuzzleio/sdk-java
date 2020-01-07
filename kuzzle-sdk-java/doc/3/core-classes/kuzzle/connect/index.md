---
code: true
type: page
title: connect
description: Connects to Kuzzle using the `host` property provided in the [websocket parameters](/sdk/java/3/protocols/websocket/constructor#arguments).
---

# connect

Connects to Kuzzle using the `host` property provided in the [websocket parameters](/sdk/java/3/protocols/websocket/constructor#arguments). Subsequent calls have no effect if the SDK is already connected.

## Arguments

```java
public void connect() throws Exception;
```

## Exceptions

Throws an `Exception` when an error occurs during the connection.

## Usage

<<< ./snippets/connect.java
