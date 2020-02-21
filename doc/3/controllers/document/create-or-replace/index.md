---
code: true
type: page
title: createOrReplace
description: Creates or Replace a new document
---

# createOrReplace

Creates or replace a document in the provided index and collection.

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> createOrReplace(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> createOrReplace(
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
| `id`               | <pre>String</pre>                            | Document ID                       |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Document content                  |
| `options`          | <pre>ConcurrentHashMap<String, Object></pre> | Optional parameters               |

---

### options

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `queuable`         | <pre>Boolean</pre>                           | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Document content                 |
| `_id`        | <pre>String</pre>            | ID of the document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/createOrReplace.java
