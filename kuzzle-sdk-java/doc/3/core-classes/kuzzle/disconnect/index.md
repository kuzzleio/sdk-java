---
code: true
type: page
title: disconnect
description: Closes the current connection to Kuzzle.
---

# disconnect

Closes the current connection to Kuzzle. The SDK then enters the `offline` state. A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

If there are still pending requests during the `disconnect` call, a `discarded` event will be issued for each of them.

## Arguments

```java
public void disconnect();
```

## Usage

<<< ./snippets/disconnect.java