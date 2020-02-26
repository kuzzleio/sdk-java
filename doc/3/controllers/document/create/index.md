---
code: true
type: page
title: create
description: Creates a new document
---

# create

Creates a new document in the provided index and collection.

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document,
      final ConcurrentHashMap<String, Object> options)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Content of the document to create |
| `options`          | <pre>ConcurrentHashMap<String, Object></pre> | Optional parameters               |

---

### options

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `id`               | <pre>String</pre> (optional)                 | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Created document                 |
| `_id`        | <pre>String</pre>            | ID of the newly created document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create.java
