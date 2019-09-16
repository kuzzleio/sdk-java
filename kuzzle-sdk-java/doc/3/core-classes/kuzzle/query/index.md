---
code: true
type: page
title: query
description: Base method used to send queries to Kuzzle.
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation](/core/1/api).

## Arguments

```java
public CompletableFuture<Response> query(ConcurrentHashMap<String, Object> query)
            throws InternalException, NotConnectedException;
```

| Argument | Type                                         | Description |
|----------|----------------------------------------------|-------------|
| `query`  | <pre>ConcurrentHashMap<String, Object></pre> | API query   |

### query

All properties necessary for the Kuzzle API can be added in the query object.
The following properties are the most common.

| Property     | Type                                         | Description                              |
|--------------|----------------------------------------------|------------------------------------------|
| `controller` | <pre>String</pre>                            | Controller name (mandatory)              |
| `action`     | <pre>String</pre>                            | Action name (mandatory)                  |
| `body`       | <pre>ConcurrentHashMap<String, Object></pre> | Query body for this action               |
| `index`      | <pre>String</pre>                            | Index name for this action               |
| `collection` | <pre>String</pre>                            | Collection name for this action          |
| `_id`        | <pre>String</pre>                            | id for this action                       |
| `volatile`   | <pre>ConcurrentHashMap<String, Object></pre> | Additional information to send to Kuzzle |

## Return

A CompletableFuture<Response> that will contain the query response.

## Usage

<<< ./snippets/query.java