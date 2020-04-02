---
code: true
type: page
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Deletes validation specifications for a collection.

<br/>

```java
  public CompletableFuture<Void> deleteSpecifications(
      final String index,
      final String collection)
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `CompletableFuture<Void>`.

## Usage

<<< ./snippets/delete-specifications.java