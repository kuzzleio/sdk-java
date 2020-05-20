---
code: true
type: page
title: refresh
description: Forces an Elasticsearch search index update
---

# refresh

When writing or deleting documents in Kuzzle, it can take up to 1 second for search indexes to be updated, making the changes available in search results.

This API route forces an immediate refresh of search indexes.

:::info
A refresh operation comes with some performance costs.

From the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/docs-refresh.html):
> "While a refresh is much lighter than a commit, it still has a performance cost. A manual refresh can be useful when writing tests, but don’t do a manual refresh every time you index a document in production; it will hurt your performance. Instead, your application needs to be aware of the near real-time nature of Elasticsearch and make allowances for it."

:::

<br/>

```java
public CompletableFuture<Void> refresh(
      final String index,
      final String collection) throws NotConnectedException, InternalException
```

## Returns

Returns a `CompletableFuture<Void>`.

## Usage

<<< ./snippets/refresh.java
