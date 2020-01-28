---
code: true
type: page
title: UpdateMyCredentials
description: Updates the current user's credentials for the specified strategy.
---

# UpdateMyCredentials

Updates the current user's credentials for the specified strategy. The credentials to send will depend on the authentication plugin and the authentication strategy.

## Arguments

```java
CompletableFuture<ConcurrentHashMap<String, Object>> updateMyCredentials(
  final String strategy,
  final ConcurrentHashMap<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>ConcurrentHashMap<String, Object></pre> | JObject representing the credentials |

## Return

A ConcurrentHashMap representing the updated credentials with the following properties:

| Property   | Type              | Description       |
|------------|-------------------|-------------------|
| `username` | <pre>String</pre> | The Username      |
| `kuid`     | <pre>String</pre> | The user's `kuid` |

## Usage

<<< ./snippets/update-my-credentials.java
