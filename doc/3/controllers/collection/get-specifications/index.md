---
code: true
type: page
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the given index and collection.

<br/>

```java
  public CompletableFuture<ConcurrentHashMap<String, Object>> getSpecifications(
      final String index,
      final String collection)
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `ConcurrentHashMap<String, Object>` representing the collection specifications.

## Usage

<<< ./snippets/get-specifications.java