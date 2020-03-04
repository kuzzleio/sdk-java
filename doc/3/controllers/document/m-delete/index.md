---
code: true
type: page
title: mDelete
description: Deletes multiple documents
---

# mDelete

Delete multiple documents.

---

## Arguments 

```java
public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
      final String index,
      final String collection,
      final ArrayList<String> ids)
throws NotConnectedException, InternalException

```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | <pre>ArrayList<String></pre>                            | Document IDs                      |
---

## Return

A `ConcurrentHashMap<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
The `successes` array contains the successfully deleted document IDs.

Each deletion error is an object of the errors array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `reason`     | <pre>ConcurrentHashMap<String, Object></pre> | Human readable reason            |
| `status`     | <pre>Integer</pre>                           | HTTP status code |

## Usage

<<< ./snippets/m-delete.java