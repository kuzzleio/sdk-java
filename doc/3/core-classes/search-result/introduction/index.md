---
code: true
type: page
title: constructor
description: SearchResult:constructor
order: 1
---

# SearchResults

This class represents a paginated search result.  

It can be returned by the following methods:
 - [document:search](/sdk/java/3/controllers/document/search)
 - [collection:searchSpecifications](/sdk/java/3/controllers/collection/search-specifications)

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.CoreClasses.SearchResult;
```

## Properties

| Property       | Type                                                    | Description        |
| -------------- | ------------------------------------------------------- | ------------------ |
| `aggregations` | <pre>ConcurrentHashMap<String, Object></pre>            | Search aggregations (can be undefined) |
| `hits`         | <pre>ArrayList<ConcurrentHashMap<String, Object>></pre> | Page results       |
| `total`        | <pre>Integer</pre>                                      |  Total number of items that _can_ be retrieved |
| `fetched`      | <pre>Integer</pre>                                      | Number of retrieved items so far   |

### hits

Each element of the `hits` ArrayList is a `ConcurrentHashMap<String, Object>` containing the following properties:

| Property  | Type               | Description            |
| --------- | ------------------ | ---------------------- |
| `_id`     | <pre>String</pre>  | Document ID            |
| `_score`  | <pre>Integer</pre> | [Relevance score](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html) |
| `_source` | <pre>ConcurrentHashMap<String, Object></pre> | Document content |