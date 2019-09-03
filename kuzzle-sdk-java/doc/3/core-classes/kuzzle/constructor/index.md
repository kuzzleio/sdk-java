---
code: true
type: page
title: constructor
description: This constructor create a new instance of the SDK. Each instance represent a different connection to a Kuzzle server with specific options.
---

# constructor

This constructor create a new instance of the SDK. Each instance represent a different connection to a Kuzzle server with specific options.

## Arguments

```java
public Kuzzle(
    AbstractProtocol<JsonObject> networkProtocol
) throws IllegalArgumentException;

public Kuzzle(
    AbstractProtocol<JsonObject> networkProtocol,
    KuzzleOptions options
) throws IllegalArgumentException;
```

| Argument   | Type                                  | Description                       |
|------------|---------------------------------------|-----------------------------------|
| `protocol` | <pre>AbstractProtol<JsonObject></pre> | Protocol used by the SDK instance |
| `options`  | <pre>KuzzleOptions</pre>              | Kuzzle object configuration       |

### options

| Argument                 | Type<br>(default)                                                | Description                                                           |
|--------------------------|------------------------------------------------------------------|-----------------------------------------------------------------------|
| `maxQueueSize`           | <pre>int</pre><br>(`-1`)                                         | The maximum amount of elements that the queue can contains.           |
| `minTokenDuration`       | <pre>int</pre><br>(`3600000`)                                    | The minimum duration of a Token before being automatically refreshed. |
| `refreshedTokenDuration` | <pre>int</pre><br>(`3600000`)                                    | The minimum duration of a Token after refresh.                        |
| `maxRequestDelay`        | <pre>int</pre><br>(`1000`)                                       | The maximum delay between two requests to be replayed.                |
| `filter`                 | <pre>Predicate<JsonObject></pre><br>(`(JsonObject obj) -> true`) | Filter the replayed queries                                           |

## Return

A `Kuzzle` SDK instance

## Usage

<<< ./snippets/constructor.java