---
code: true
type: page
title: count
description: Counts documents in a collection.
---

# count

Counts documents in a collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.

---

## Arguments

```java
public CompletableFuture<Integer> count(
      final String index,
      final String collection)
throws NotConnectedException, InternalException

```

```java
public CompletableFuture<Integer> count(
      final String index,
      final String collection,
      final ConcurrentHaspMap<String, Object> searchQuery)
throws NotConnectedException, InternalException

```

---

| Argument     | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>string</pre> | Index name      |
| `collection` | <pre>string</pre> | Collection name |
| `query`      | <pre>object</pre> | Query to match  |

---

## Return

Returns an Integer.

## Usage

<<< ./snippets/count.java