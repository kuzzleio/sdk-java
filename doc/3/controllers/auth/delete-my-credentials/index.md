---
code: true
type: page
title: DeleteMyCredentials
description: Deletes the current user's credentials for the specified strategy.
---

# DeleteMyCredentials

Deletes the current user's credentials for the specified strategy. If the credentials that generated the current authentication token are removed, the user will remain logged in until they log out or until their session expires. After that they will no longer be able to log in with the deleted credentials.

## Arguments

```java
public CompletableFuture<Void> deleteMyCredentials(final String strategy)
  throws NotConnectedException, InternalException
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/delete-my-credentials.java
