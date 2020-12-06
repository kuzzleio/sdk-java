---
code: true
type: page
title: SubscribeOptions
description: SubscribeOptions class documentation
order: 110
---

# SubscribeOptions

This class represents the options usable with the [RealtimeController.subscribe](/sdk/java/3/controllers/realtime/subscribe) method.  

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.Options.SubscribeOptions;
```

## Properties

| Property | Type<br/>(default) | Description      | writable |
|----------|--------------------|------------------| ------- |
| `Scope`           | <pre>Scope</pre><br/>(`ALL`)   | Subscribes to document entering or leaving the scope<br/>Possible values: `ALL`, `IN`, `OUT`, `NONE`| yes |
| `Users`           | <pre>Users</pre><br/>(`NONE`)  | Subscribes to users entering or leaving the room<br/>Possible values: `ALL`, `IN`, `OUT`, `NONE`| yes |
| `SubscribeToSelf` | <pre>boolean</pre><br/>(`true`)    | Subscribes to notifications fired by our own queries | yes |
| `Volatile`        | <pre>ConcurrentHashMap<String, Object></pre><br/>(`null`) | ConcurrentHashMap representing subscription information, used in [user join/leave notifications](/core/2/guides/main-concepts/api#volatile-data)  |yes |
