---
code: true
type: page
title: list
description: Lists the indexes.
---

# list

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
