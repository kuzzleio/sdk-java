---
code: true
type: page
title: update
description: Update the collection mapping
---

# update

<SinceBadge version="Kuzzle 2.1.0" />

You can define the collection [dynamic mapping policy](/core/2/guides/main-concepts/data-storage#mappings-dynamic-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata](/core/2/guides/main-concepts/data-storage#mappings-metadata) within the `_meta` root field.

<br/>

```java
  public CompletableFuture<Void> update(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> mapping)
```

<br/>

| Arguments    | Type                                         | Description                                                                                                                                                                   |
| ------------ | -------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `index`      | <pre>String</pre>                            | Index name                                                                                                                                                                    |
| `collection` | <pre>String</pre>                            | Collection name                                                                                                                                                               |
| `mapping`    | <pre>ConcurrentHashMap<String, Object></pre> | Describes the collection mapping  |

### mapping

A `ConcurrentHashMap<String, Object>` representing the collection data mapping.

It must have a root field `properties` that contain the mapping definition:

```java
{
  mappings={
    dynamic="[true|false|strict]",
    _meta={
      field="value"
  },
    properties={
      field1={ type='text' },
      field2={
        properties={
          nestedField={ type='keyword' }
        }
      }
    }
  }
};
```

More information about database mappings [here](/core/2/guides/main-concepts/data-storage).

## Returns

Returns a `CompletableFuture<Void>`.

## Usage

<<< ./snippets/update.java
