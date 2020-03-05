---
code: false
type: page
title: Network Loss Resilience
description: Learn how to use the Kuzzle JAVA SDK with an instable network
order: 400
---

# Network Loss Resilience

The Kuzzle JAVA SDK provides tools that allow it to be used with an unstable network connection.

## Automatic reconnection & resubscription

The Kuzzle JAVA SDK can handle auto reconnection in case of a network disconnection and also resubscribe to all subscriptions if there is any.

To enable the auto reconnection with WebSocket (enabled by default) refer to the `autoReconnect` options of the [WebSocketOptions](/sdk/java/3/protocols/websocket-options) object.

To enable the auto resubscribe (enabled by default) refer to the `autoResubscribe` options of the [KuzzleOptions](/sdk/java/3/core-classes/kuzzle-options) object.
