---
code: true
type: page
title: CreateMyCredentials
description: Create the current user's credentials for the specified strategy.
---

# CreateMyCredentials

Creates the current user's credentials for the specified strategy.

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> createMyCredentials(final String strategy,
  final ConcurrentHashMap<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>ConcurrentHashMap<String, Object></pre> | ConcurrentHashMap representing the credentials |

## Return

A ConcurrentHashMap representing the new credentials.

## Usage

<<< ./snippets/create-my-credentials.java
