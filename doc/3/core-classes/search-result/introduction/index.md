---
code: true
type: page
title: constructor
description: SearchResult:constructor
order: 1
---

# Constructor

This object can only be instantiated internally by this SDK, and is an easy-to-use representation of a paginated result from a [search](/sdk/java/2/core-classes/collection/search) or a [scroll](/sdk/java/2/core-classes/collection/scroll) request.

---

## Properties

| Property name  | Type       | Description                                               | get/set |
| -------------- | ---------- | --------------------------------------------------------- | ------- |
| `aggregations` | ConcurrentHashMap<String, Object>     | The result of an aggregation produced by a search request | get     |
| `collection`   | Collection | The collection associated to this document                | get     |
| `documents`    | ArrayList<ConcurrentHashMap<String, Object>> | An array of instantiated Document objects                 | get     |
| `fetched`      | Integer     | The number of fetched documents so far                    | get/set |
| `options`      | SearchOptions     | The arguments of the search/scroll request                | get     |
| `filters`      | object     | The filters of the search request                         | get     |
| `total`        | Integer    | The total number of results that can be fetched           | get     |

---