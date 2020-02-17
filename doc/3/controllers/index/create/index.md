---
code: true
type: page
title: Create
description: Creates an index.
---

# Create

Creates a new index in Kuzzle

## Arguments

```java
CompletableFuture<Void> create(final String index) 
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/create.java
