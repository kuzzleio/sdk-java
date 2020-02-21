---
code: true
type: page
title: delete
description: Deletes a new document
---

# delete

Deletes a document in the provided index and collection.

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> delete(
      final String index,
      final String collection,
      final String id)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> delete(
      final String index,
      final String collection,
      final String id,
      final Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `ConcurrentHashMap` which has the following property:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>            | ID of the deleted document                       |

## Usage

<<< ./snippets/delete.java
