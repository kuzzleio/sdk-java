---
code: true
type: page
title: MDelete
description: Deletes multiple indexes.
---

# MDelete

Deletes multiple indexes.

## Arguments

```java
CompletableFuture<ArrayList<String>> mDelete(final ArrayList<String> indexes) 
  throws NotConnectedException, InternalException
```

| Argument  | Type              | Description           |
|-----------|-------------------|-----------------------|
| `indexes` | <pre>ArrayList<String></pre> | List of indexes names |

## Return

Returns an `ArrayList<String>` containing the list of indexes names deleted.

## Usage

<<< ./snippets/mDelete.java
