---
code: true
type: page
title: GetStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# GetStrategies

Gets all authentication strategies registered in Kuzzle.

## Arguments

```java
public CompletableFuture<ArrayList<String>> getStrategies()
  throws NotConnectedException, InternalException
```

## Return

An ArrayList representing the available authentication strategies.

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/get-strategies.java
