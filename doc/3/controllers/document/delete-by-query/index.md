---
code: true
type: page
title: deleteByQuery
description: Delete documents matching query
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

An empty or null query will match all documents in the collection.

<br/>

```java
  public CompletableFuture<ArrayList<String>> deleteByQuery(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Object></pre> | Query to match  |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Returns

Returns an `ArrayList<String>` containing the deleted document ids.

## Usage

<<< ./snippets/delete-by-query.java