---
code: false
type: page
title: Constructor 
description: Creates a new Kuzzle object connected to the backend
order: 100
---

# Constructor

Use this constructor to create a new instance of the SDK.
Each instance represents a different connection to a Kuzzle server with specific options.

## Arguments

```java
public Kuzzle(
  AbstractProtocol networkProtocol,
  KuzzleOptions options
) throws IllegalArgumentException
```

<br/>

| Argument   | Type                | Description                       |
| ---------- | ------------------- | --------------------------------- |
| `networkProtocol` | <pre>AbstractProtocol</pre> | Protocol used by the SDK instance |
| `options` | <pre>KuzzleOptions</pre> | Class which contains options |

## networkProtocol

The protocol used to connect to the Kuzzle instance.
It can be one of the following available protocols:

- [WebSocket](/sdk/java/3/protocols/websocket)

## Return

The `Kuzzle` SDK instance.

## Throws

Can throw a [IllegalArgumentException](/sdk/java/3/exceptions/IllegalArgumentException)

## Usage

<<< ./snippets/constructor.java