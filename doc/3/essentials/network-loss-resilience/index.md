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

The Kuzzle JAVA SDK can automatically reconnect in case of a network disconnection and it can renew realtime subscriptions if there are any.

To control the auto reconnection feature with WebSocket (enabled by default), refer to the `autoReconnect` option of the [WebSocketOptions](/sdk/java/3/protocols/websocket-options) object.

To control the auto resubscription (enabled by default), refer to the `autoResubscribe` option of the [KuzzleOptions](/sdk/java/3/core-classes/kuzzle-options) object.
