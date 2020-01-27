---
code: true
type: page
title: GetMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# GetMyRights

Returns the rights for the currently logged in user within the SDK instance.

## Arguments

```java
public CompletableFuture<ArrayList<Object>> getMyRights()
  throws NotConnectedException, InternalException
```

## Return

An ArrayList object.

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/get-my-rights.java
