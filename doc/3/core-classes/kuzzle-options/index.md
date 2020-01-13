---
code: true
type: page
title: KuzzleOptions
description: KuzzleOptions class documentation
order: 110
---

# KuzzleOptions

This class represents the options usable with the Kuzzle class.  

It can be used with the following methods:
 - [Kuzzle.Kuzzle](/sdk/java/3/core-classes/kuzzle)

# Consutructor

This class has a constructor and a constructor by copy.

## Getters and setters

| Property | Type | Description |
|--- |--- |--- |
| `From` | <pre>int</pre> | Offset of the first document to fetch |
| `Scroll` | <pre>string</pre> |  When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/common-options.html#time-units)) |
| `Size` | <pre>int</pre> | Maximum number of documents to retrieve per page |
| `Sort` | <pre>string</pre> | Field to sort the result on |