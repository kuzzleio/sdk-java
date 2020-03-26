---
code: true
type: page
title: create
description: Creates a new collection
---

# create

Creates a new [collection](/core/2/guides/essentials/store-access-data) in Kuzzle via the persistence engine, in the provided index.

You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/elastic-stack) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/mapping.html)).

This method will only update the mapping if the collection already exists.

<br/>

---

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> mapping)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `mapping`          | <pre>object</pre>                            | Describes the data mapping to associate to the new collection, using Elasticsearch [mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/mapping.html) |

---

### mapping

A `ConcurrentHashMap<String, Object>` representing the data mapping of the collection.

The mapping must have a root field `properties` that contain the mapping definition:

```java
  ConcurrentHashMap<String, Object> mapping = new ConcurrentHashMap<>();
  ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
  ConcurrentHashMap<String, Object> field = new ConcurrentHashMap<>();

  field.put("type", "keyword");
  properties.put("field", field);
  mapping.put("properties", properties);
```

More informations about database mappings [here](/core/2/guides/essentials/database-mappings).

## Return

A `ConcurrentHashMap` which has an `acknowledged` Boolean.

## Usage

<<< ./snippets/create.java
