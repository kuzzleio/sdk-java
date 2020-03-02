---
code: true
type: page
title: replace
description: Replaces a document
---

# replace

Replaces the content of an existing document.

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> replace(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> replace(
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
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Updated ocument content                  |
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

<<< ./snippets/replace.java
