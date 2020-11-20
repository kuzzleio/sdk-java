---
code: true
type: page
title: list
description: Returns the collection list of an index
---

# list

Returns the list of collections associated to a provided index.
The returned list is sorted in alphanumerical order.

<br/>

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> list(
      final String index) throws NotConnectedException, InternalException
```


| Arguments | Type                   | Description   |
| --------- | ---------------------- | ------------- |
| `index`   | <pre>String</pre>      | Index name    |

## Returns

Returns a `ConcurrentHashMap<String, Object>` containing the following properties:

| Property      | Type                | Description                                                        |
| ------------- | ------------------- | ------------------------------------------------------------------ |
| `type`        | <pre>String</pre>   | Types of returned collections <br/>(`all`, `realtime` or `stored`) |
| `collections` | `ArrayList<Object>` | List of collections                                                |
| `from`        | <pre>Integer</pre>   | Offset of the first result                                         |
| `size`        | <pre>Integer</pre>   | Maximum number of returned results                                 |

Each object in the `collections` array contains the following properties:

| Property | Type              | Description                              |
| -------- | ----------------- | ---------------------------------------- |
| `name`   | <pre>String</pre> | Collection name                          |
| `type`   | <pre>String</pre> | Collection type (`realtime` or `stored`) |

## Usage

<<< ./snippets/list.java