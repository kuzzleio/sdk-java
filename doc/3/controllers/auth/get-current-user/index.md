---
code: true
type: page
title: GetCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`.
---

# GetCurrentUserAsync

Returns informations about the user currently loggued with the SDK instance.

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> getCurrentUser()
  throws NotConnectedException, InternalException
```

## Return

A ConcurrentHashMap representing the User.

| Property     | Type               | Description                                       |
|--------------|--------------------|---------------------------------------------------|
| `_id`        | <pre>String</pre>  | Representing the current user `kuid`              |
| `strategies` | <pre>Array</pre>  | Available authentication strategies for that user |
| `_source`    | <pre>ConcurrentHashMap</pre> | User information                                  |

## Throws

A `NotConnectedException`, `InternalException` if there is an error.

## Usage

<<< ./snippets/get-current-user.java
