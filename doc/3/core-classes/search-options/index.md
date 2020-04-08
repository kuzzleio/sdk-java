---
code: true
type: page
title: SearchOptions
description: SearchOptions class documentation
order: 110
---

# SearchOptions

This class represents the options usable with the search related methods.  

It can be used with the following methods:
 - [document:search](/sdk/java/3/controllers/document/search)
 - [collection:searchSpecifications](/sdk/java/3/controllers/collection/search-specifications)

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.Options.SearchOptions;
```

## Properties

| Property | Type                  | Description                           |
| -------- | --------------------- | ------------------------------------- |
| `from`   | <pre>Integer</pre>    | Offset of the first document to fetch |
| `scroll` | <pre>String</pre>     | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/common-options.html#time-units)) |
| `size`   | <pre>Integer</pre>    | Maximum number of documents to retrieve per page |