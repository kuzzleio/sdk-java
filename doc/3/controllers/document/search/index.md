---
code: true
type: page
title: search
description: Searches a document
---

# search

Searches document.

There is a limit to how many documents can be returned by a single search query.
That limit is by default set at 10000 documents, and you can't get over it even with the from and size pagination options.

:::info
When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using [SearchResult.next](/sdk/java/3/core-classes/search-result/next) rather than increasing the size parameter.
:::

::: warning
When using a cursor with the `scroll` option, Elasticsearch has to duplicate the transaction log to keep the same result during the entire scroll session.  
It can lead to memory leaks if a scroll duration too great is provided, or if too many scroll sessions are open simultaneously.  
:::

::: info
<SinceBadge version="Kuzzle 2.2.0"/>
You can restrict the scroll session maximum duration under the `services.storage.maxScrollDuration` configuration key.
:::

---

## Arguments
 
```java
public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>>> search(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final SearchOptions options) throws NotConnectedException, InternalException

```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `searchQuery`      | <pre>ConcurrentHashMap</pre>                 | Search query                      |
| `options`          | <pre>SearchOptions</pre>                     | Query options                     |
---

### searchQuery body properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

### options

A [SearchOptions](/sdk/java/3/core-classes/search-options) object.

The following options can be set:

| Options    | Type<br/>(default)              | Description                                                                                                                                                                                                       |
| ---------- | ------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `from`     | <pre>Integer</pre><br/>(`0`)    | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>Integer</pre><br/>(`10`)   | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/common-options.html#time-units)) |

## Return

Returns a [SearchResult](/sdk/java/3/core-classes/search-result) object.

## Usage

<<< ./snippets/search.java
