---
code: true
type: page
title: CredentialsExist
description: Checks that the current user has credentials for the specified strategy.
---

# CredentialsExist

Checks that the current user has credentials for the specified strategy.

## Arguments

```java
public CompletableFuture<Boolean> credentialsExist(final String strategy)
  throws NotConnectedException, InternalException
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

A boolean indicating if credentials exist for the strategy.

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/credentials-exist.java
