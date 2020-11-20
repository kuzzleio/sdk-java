---
code: true
type: page
title: mGet
description: Gets multiple documents
---

# mGet

Gets multiple documents.

---

## Arguments 

```java
public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mGet(
      final String index,
      final String collection,
      final ArrayList<String> ids)
throws NotConnectedException, InternalException

```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | `ArrayList<String>`                            | Document IDs                      |
---

## Return

A `ConcurrentHashMap<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Object></pre> | Document content                 |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |

The `errors` array contain the IDs of not found documents.

## Usage

<<< ./snippets/m-get.java
