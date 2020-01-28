---
code: true
type: page
title: Logout
description: Revokes the user's token & unsubscribe them from registered rooms.
---

# Logout

Revokes the user's token & unsubscribes them from registered rooms.

## Arguments

```java
public CompletableFuture<Response> logout()
  throws NotConnectedException, InternalException
```

## Return

A [Response](/sdk/java/3/core-classes/response) object.

## Usage

<<< ./snippets/logout.java
