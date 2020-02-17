---
code: true
type: page
title: List
description: List the indexes.
---

# List

Gets the complete list of indexes handled by Kuzzle.

## Arguments

```java
CompletableFuture<ArrayList<String>> list() 
  throws NotConnectedException, InternalException
```

## Return

Returns an `ArrayList<String>` containing the list of index names handled by Kuzzle.

## Usage

<<< ./snippets/list.java
