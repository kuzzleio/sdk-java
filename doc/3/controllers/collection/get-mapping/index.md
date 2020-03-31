---
code: true
type: page
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the collection mapping.

<br/>

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> getMapping(
      final String index,
      final String collection) throws NotConnectedException, InternalException
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `ConcurrentHashMap<String, Object>` representing the collection mapping.

## Usage

<<< ./snippets/get-mapping.js