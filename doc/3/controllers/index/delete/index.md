---
code: true
type: page
title: Delete
description: Deletes an index.
---

# Delete

Deletes an entire index from Kuzzle.

## Arguments

```java
CompletableFuture<Void> delete(final String index)
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/delete.java
