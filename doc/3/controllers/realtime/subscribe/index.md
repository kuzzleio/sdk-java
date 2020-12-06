---
code: true
type: page
title: subscribe
description: Subscribes to real-time notifications.
---

# Subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications](/core/2/api/payloads/notifications), sent to you in real-time by Kuzzle.

## Arguments

```java
public CompletableFuture<String> subscribe(
  final String index, 
  final String collection, 
  final ConcurrentHashMap<String, Object> filters, 
  final NotificationHandler handler, 
  final SubscribeOptions options
) throws NotConnectedException, InternalException
```

| Argument     | Type                                    | Description                                                                                                    |
|--------------|-----------------------------------------|----------------------------------------------------------------------------------------------------------------|
| `index`      | <pre>String</pre>                       | Index name                                                                                                     |
| `collection` | <pre>String</pre>                       | Collection name                                                                                                |
| `filters`    | <pre>ConcurrentHashMap<String, Object></pre>                      | ConcurrentHashMap representing a set of filters following [Koncorde syntax](/core/2/api/koncorde-filters-syntax) |
| `handler`   | <pre>NotificationHandler</pre>          | Handler function to handle notifications                                                                      |
| `options`    | <pre>SubscribeOptions</pre><br>(`null`) | Subscription options                                                                                           |

### handler

Handler function that will be called each time a new notification is received.
The hanlder will receive a [Response](/sdk/java/3/essentials/realtime-notifications) as its only argument.

### options

A [SubscribeOptions](/sdk/java/3/core-classes/subscribe-options) object.

## Return

The room ID.

## Usage

_Simple subscription to document notifications_

<<< ./snippets/document-notifications.java

_Subscription to document notifications with scope option_

<<< ./snippets/document-notifications-leave-scope.java
