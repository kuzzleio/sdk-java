---
code: true
type: page
title: truncate
description: Remove all documents from collection
---

# truncate

Removes all documents from a collection, while keeping the associated mapping.

<br/>

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> truncate(
      final String index,
      final String collection) throws NotConnectedException, InternalException
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

A `ConcurrentHashMap` which has an `acknowledged` Boolean.

## Usage

<<< ./snippets/truncate.js