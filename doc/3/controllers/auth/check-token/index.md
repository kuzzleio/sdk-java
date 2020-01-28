---
code: true
type: page
title: CheckToken
description: Checks an authentication Token's validity.
---

# CheckToken

Checks an authentication token's validity.

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> checkToken(final String token)
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `token`  | <pre>String</pre> | Authentication token   |

## Return

A ConcurrentHashMap which has the following properties:

| Property     | Type              | Description                      |
|--------------|-------------------|----------------------------------|
| `valid`      | <pre>boolean</pre>   | Token validity                   |
| `state`      | <pre>String</pre> | Explain why the token is invalid |
| `expires_at` | <pre>int</pre>  | Token expiration timestamp       |

## Usage

<<< ./snippets/check-token.java