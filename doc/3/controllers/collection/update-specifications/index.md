---
code: true
type: page
title: updateSpecifications
description: Update the validation specifications
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for a collection.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

<br/>

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> updateSpecifications(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> specifications)
```

<br/>

| Arguments        | Type                                         | Description              |
| ---------------- | -------------------------------------------- | ------------------------ |
| `index`          | <pre>String</pre>                            | Index name               |
| `collection`     | <pre>String</pre>                            | Collection name          |
| `specifications` | <pre>ConcurrentHashMap<String, Object></pre> | Specifications to update |

### specifications

A `ConcurrentHashMap<String, Object>` representing the specifications.

It must follow the [Specification Structure](/core/2/guides/advanced/data-validation).

## Returns

Returns a `ConcurrentHashMap<String, Object>` containing the specifications.

## Usage

<<< ./snippets/update-specifications.java