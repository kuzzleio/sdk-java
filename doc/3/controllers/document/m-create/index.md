---
code: true
type: page
title: mCreate
description: Creates multiple documents
---

# mCreate

Creates multiple documents in the provided index and collection.

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> mCreate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> mCreate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents,
      final Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index                             |
| `collection`       | <pre>String</pre>                                       | Collection                        |
| `documents`        | <pre>ArrayList<ConcurrentHashMap<String, Object>></pre> | ArrayList containing the documents to create |
| `waitForRefresh`   | <pre>Boolean</pre> | Optional parameters                |

---

### documents

Each document has the following properties:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `_id`              | <pre>String</pre> (optional)                 | Optional document ID. Will be auto-generated if not defined.             |
| `body`             | <pre>Boolean</pre> (optional)                | Document body |

## Return

A `ConcurrentHashMap` which has a `successes` and `errors` array:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Created document                 |
| `_id`        | <pre>String</pre>            | ID of the newly created document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `document`   | <pre>ConcurrentHashMap</pre> | Document that causes the error   |
| `status`     | <pre>Integer</pre>           | HTTP error status                |
| `reason`     | <pre>String</pre>            | Human readable reason |

## Usage

<<< ./snippets/m-create.java
