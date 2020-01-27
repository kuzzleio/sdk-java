---
code: true
type: page
title: UpdateSelf
description: Updates the current user object in Kuzzle.
---

# UpdateSelf

Updates the current user object in Kuzzle.

## Arguments

```java
CompletableFuture<ConcurrentHashMap<String, Object>> updateSelf(
  final ConcurrentHashMap<String, Object> content)
  throws NotConnectedException, InternalException
```

| Argument  | Type               | Description                           |
|-----------|--------------------|---------------------------------------|
| `content` | <pre>ConcurrentHashMap<String, Object></pre> | Hashmap representing the user content |

## Return

Return a JObject with the following properties:

| Property  | Type               | Description                               |
|-----------|--------------------|-------------------------------------------|
| `_id`     | <pre>String</pre>  | User's `kuid`                             |
| `_source` | <pre>ConcurrentHashMap<String, Object></pre> | Additional (and optional) user properties |

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/update-self.java
