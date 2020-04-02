---
code: true
type: page
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store or modify the existing specification.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

<br/>

```java
  public CompletableFuture<ConcurrentHashMap<String, Object>> validateSpecifications(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> specifications)
```

<br/>

| Arguments        | Type                                         | Description                |
| ---------------- | -------------------------------------------- | -------------------------- |
| `index`          | <pre>String</pre>                            | Index name                 |
| `collection`     | <pre>String</pre>                            | Collection name            |
| `specifications` | <pre>ConcurrentHashMap<String, Object></pre> | Specifications to validate |

### specifications

A `ConcurrentHashMap<String, Object>` representing the specifications.

This object must follow the [Specification Structure](/core/2/guides/cookbooks/datavalidation):

```java
{
  strict=<boolean>,
  fields={
    // ... specification for each field
  }
}
```

## Returns

Returns a `ConcurrentHashMap<String, Object>` which contains information about the specifications validity.

It contains the following properties:

| Property      | Type                         | Description                  |
| ------------- | ---------------------------- | ---------------------------- |
| `valid`       | <pre>Boolean</pre>           | Specifications validity      |
| `details`     | <pre>ArrayList<String></pre> | Specifications errors        |
| `description` | <pre>String</pre>            | Global description of errors |

## Usage

<<< ./snippets/validate-specifications.java