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
      final UpdateOptions options)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                        |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Partial document content |
| `options`          | <pre>UpdateOptions</pre><br>(`null`)         | Optional parameters               |

---

### options

A [UpdateOptions](/sdk/java/3/core-classes/update-options) object.

The following options can be set:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |           |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `retryOnConflict`  | <pre>Integer</pre>                           | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Updated document (If source option set to true)                |
| `_id`        | <pre>String</pre>            | ID of the updated document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/update.java
