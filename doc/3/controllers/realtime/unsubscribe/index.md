---
code: true
type: page
title: unsubscribe
description: Removes a subscription.
---

# unsubscribe

Removes a subscription.

## Arguments

```java
public CompletableFuture<Void> unsubscribe(final String roomId) 
  throws NotConnectedException, InternalException
```

| Argument  | Type               | Description          |
|-----------|--------------------|----------------------|
| `room_id` | <pre>String</pre>  | Subscription room ID |

## Usage

<<< ./snippets/unsubscribe.java
