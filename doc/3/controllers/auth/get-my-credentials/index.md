---
code: true
type: page
title: GetMyCredentials
description: Returns the current user's credential information for the specified strategy.
---

# GetMyCredentials

Returns the current user's credential information for the specified strategy. The data returned will depend on the specified strategy. The result can be empty.

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> getMyCredentials()
  throws NotConnectedException, InternalException
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

Returns a ConcurrentHashMap representing the credentials for the provided authentication strategy.

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/get-my-credentials.java
