---
code: true
type: page
title: get
description: Gets a document
---

# get

Gets a document.

---

## Arguments
 
```java
public CompletableFuture<ConcurrentHashMap<String, Object>> get(
      final String index,
      final String collection,
      final String id)
throws NotConnectedException, InternalException

```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                       |

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Document content                |
| `_id`        | <pre>String</pre>            | ID of the document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/get.java
