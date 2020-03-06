---
code: true
type: page
title: createOrReplace
description: Creates or replaces a document
---

# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

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
      final Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id`               | <pre>String</pre>                            | Document ID                       |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Document content                  |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Document content                 |
| `_id`        | <pre>String</pre>            | ID of the document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create-or-replace.java
