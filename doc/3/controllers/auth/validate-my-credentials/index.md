---
code: true
type: page
title: ValidateMyCredentials
description: Validate the current user's credentials for the specified strategy.
---

# ValidateMyCredentials

Validate the current user's credentials for the specified strategy. The `result` field is `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

## Arguments

```java
CompletableFuture<Boolean> validateMyCredentials(final String strategy,
  final ConcurrentHashMap<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>ConcurrentHashMap<String, Object></pre> | A Hashmap representing the credentials |

## Return

A boolean indicating if the credentials are valid.

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/validate-my-credentials.java
