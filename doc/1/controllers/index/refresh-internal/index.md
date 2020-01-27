---
code: true
type: page
title: refreshInternal
description: Force refresh of Kuzzle internal index
---

# RefreshInternal

When writing or deleting security and internal documents (users, roles, profiles, configuration, etc.) in Kuzzle, the update needs to be indexed before being reflected in the search index.

The `refreshInternal` action forces a [refresh](/sdk/java/1/controllers/index/refresh), on the internal index, making the documents available to search immediately.

:::info
A refresh operation comes with some performance costs.

From the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-refresh.html):
> "While a refresh is much lighter than a commit, it still has a performance cost. A manual refresh can be useful when writing tests, but don’t do a manual refresh every time you index a document in production; it will hurt your performance. Instead, your application needs to be aware of the near real-time nature of Elasticsearch and make allowances for it."

:::

## Arguments

```java
void refreshInternal() throws io.kuzzle.sdk.KuzzleException;
void refreshInternal(io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.KuzzleException;
```

<br/>

| Arguments | Type         | Description       |
| --------- | ------------ | ----------------- |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options | 

### Options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling).

## Usage

<<< ./snippets/refreshInternal.java