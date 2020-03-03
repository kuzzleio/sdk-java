---
code: true
type: page
title: count
description: Returns the number of other connections sharing the same subscription.
---

# count

Returns the number of other connections sharing the same subscription.

## Arguments

```java
public CompletableFuture<Integer> count(final String roomId)
  throws NotConnectedException, InternalException
```

| Argument  | Type              | Description          |
|-----------|-------------------|----------------------|
| `room_id` | <pre>String</pre> | Subscription room ID |

## Return

Returns the number of active connections using the same provided subscription room.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error](/sdk/java/3/essentials/error-handling).

## Usage

<<< ./snippets/count.java
