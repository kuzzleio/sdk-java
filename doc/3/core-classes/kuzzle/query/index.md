---
code: true
type: page
title: Query
description: Base method to send API query to Kuzzle
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation](/core/2/api).

:::warning
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
:::

## Arguments

```java
public CompletableFuture<Response> query(final ConcurrentHashMap<String, Object> query)
      throws InternalException, NotConnectedException
```

<br/>

| Argument  | Type              | Description            |
| --------- | ----------------- | ---------------------- |
| `query` | <pre>ConcurrentHashMap<String, Object></pre> | API request    |

### query

All properties necessary for the Kuzzle API can be added in the query object.
The following properties are the most common.

| Property     | Type              | Description                              |
| ------------ | ----------------- | ---------------------------------------- |
| `controller` | <pre>String</pre> | Controller name (mandatory)              |
| `action`     | <pre>String</pre> | Action name (mandatory)                  |
| `body`       | <pre>ConcurrentHashMap<String, Object></pre> | Query body for this action               |
| `index`      | <pre>String</pre> | Index name for this action               |
| `collection` | <pre>String</pre> | Collection name for this action          |
| `_id`        | <pre>String</pre> | id for this action                       |
| `volatile`   | <pre>ConcurrentHashMap<String, Object></pre> | Additional information to send to Kuzzle |

## Returns

Returns a [Response](/sdk/java/3/core-classes/response) object which represent a raw Kuzzle API response. See the [API Documentation](/core/2/api).

## Usage

<<< ./snippets/query.java
