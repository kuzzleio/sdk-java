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

# Constructor

This class has a constructor and a constructor by copy.

## Getters and setters

### queueMaxSize

The maximum amount of elements that the queue can contains. If set to -1, the size is unlimited.

```java
public int getMaxQueueSize();
public KuzzleOptions setMaxQueueSize(int maxQueueSize)
```

### minTokenDuration

The minimum duration of a Token before being automatically refreshed. If set to -1 the SDK does not refresh the token automatically.

```java
public int getMinTokenDuration()
public KuzzleOptions setMinTokenDuration(int minTokenDuration)
```

### refreshedTokenDuration

The minimum duration of a Token after refresh. If set to -1 the SDK does not refresh the token automatically.

```java
public int getRefreshedTokenDuration()
public KuzzleOptions setRefreshedTokenDuration(int refreshedTokenDuration)
```

### maxRequestDelay

The maximum delay between two requests to be replayed.

```java
public int getMaxRequestDelay()
public KuzzleOptions setMaxRequestDelay(int maxRequestDelay)
```

### autoResubscribe

Automatically renew all subscriptions on a `reconnected` event.

```java
public boolean isAutoResubscribe();
public KuzzleOptions setAutoResubscribe(boolean autoResubscribe);
```
