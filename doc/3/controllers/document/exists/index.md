---
code: true
type: page
title: exists
description: Checks if a document exists
---

# exists

Checks if a document exists.

---

## Arguments

```java
public CompletableFuture<Boolean> exists(
      final String index,
      final String collection,
      final String id)
throws NotConnectedException, InternalException

```

---

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |


---

## Return

Returns a boolean.

## Usage

<<< ./snippets/exists.java