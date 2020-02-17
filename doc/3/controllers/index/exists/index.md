---
code: true
type: page
title: Exists
description: Check for index existence.
---

# Exists

Checks if the given index exists in Kuzzle.

## Arguments

```java
CompletableFuture<Boolean> exists(final String index) 
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Return

Returns a `boolean` that indicates whether the index exists or not.

## Usage

<<< ./snippets/exists.java
