---
code: true
type: page
title: updateByQuery
description: Updates documents matching query
---

# updateByQuery

Updates documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

An empty or null query will match all documents in the collection.

<br/>

```java
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final ConcurrentHashMap<String, Object> changes) throws NotConnectedException, InternalException

  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final ConcurrentHashMap<String, Object> changes,
      final UpdateOptions options) throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Object></pre> | Query to match  |
| `changes`          | <pre>ConcurrentHashMap<String, Object></pre> | Partial changes to apply to the documents |
| `options`          | <pre>UpdateOptions</pre><br>(`null`)         | Optional parameters               |

---

### options

A [UpdateOptions](/sdk/java/3/core-classes/update-options) object.

The following options can be set:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |

## Returns

A `ConcurrentHashMap<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each updated document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Object></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the udated document                   |
| `status`     | <pre>Integer</pre>                           | HTTP status code |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>ConcurrentHashMap<String, Object></pre> | Document that causes the error   |
| `status`     | <pre>Integer</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

<<< ./snippets/update-by-query.js