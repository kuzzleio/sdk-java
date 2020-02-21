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
      final ConcurrentHashMap<String, Object> options)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |
| `options`          | <pre>ConcurrentHashMap<String, Object></pre> | Optional parameters               |

---

### options

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `queuable`         | <pre>Boolean</pre> (optional)                | If true, queues the request during downtime, until connected to Kuzzle again   |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

## Return

A `ConcurrentHashMap` which has the following property:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>            | ID of the deleted document                       |

## Usage

<<< ./snippets/delete.java
