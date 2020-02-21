---
code: true
type: page
title: update
description: Updates a document
---

# update

Updates a document.

---

## Arguments
 
```java
public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document,
      final ConcurrentHashMap<String, Object> options)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                        |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Partial content of the document to update |
| `options`          | <pre>ConcurrentHashMap<String, Object></pre> | Optional parameters               |

---

### options

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `queuable`         | <pre>boolean</pre>                           | If true, queues the request during downtime, until connected to Kuzzle again              |
| `waitForRefresh`   | <pre>boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `retryOnConflict`  | <pre>Number</pre>                            | The number of times the database layer should retry in case of version conflict
| `source`           | <pre>boolean</pre>                           | If true, returns the updated document inside the response

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Updated document (If source option set to true)                |
| `_id`        | <pre>String</pre>            | ID of the updated document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/update.java
