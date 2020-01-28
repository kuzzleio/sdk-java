---
code: true
type: page
title: Login
description: Authenticates a user.
---

# Login

Authenticates a user.

If this action is successful, all further requests emitted by this SDK instance will be in the name of the authenticated user, until either the authenticated token expires, the [logout](/sdk/java/3/controllers/auth/logout) action is called, or the authentication token property is manually unset.

## Arguments

```java
CompletableFuture<ConcurrentHashMap<String, Object>> login(
  final String strategy,
  final ConcurrentHashMap<String, Object> credentials,
  final String expiresIn) 
  throws NotConnectedException, InternalException
```

<br/>

| Argument      | Type                 | Description                          |
|---------------|----------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>    | Strategy to use                      |
| `credentials` | <pre>ConcurrentHashMap<String, Object></pre>   | Hashmap representing the credentials |
| `expiresIn`   | <pre>String</pre> | Token duration                       |

#### strategy

The name of the authentication [strategy](/core/2/guides/kuzzle-depth/authentication/#authentication) used to log the user in.

Depending on the chosen authentication `strategy`, additional [credential arguments](/core/2/guides/kuzzle-depth/authentication/#authentication) may be required.
The API request example on this page provides the necessary arguments for the [`local` authentication plugin](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local).

Check the appropriate [authentication plugin](/core/2/plugins/guides/strategies/overview) documentation to get the list of additional arguments to provide.


### expiresIn

The default value for the `expiresIn` option is defined at server level, in Kuzzle's [configuration file](/core/2/guides/essentials/configuration).


## Return

Returns a Hashmap with the following properties:

| Property    | Type              | Description                                                                              |
|-------------|-------------------|------------------------------------------------------------------------------------------|
| `_id`       | <pre>String</pre> | User's `kuid`                                                                            |
| `jwt`       | <pre>String</pre> | Encrypted authentication token, that must then be sent in the requests headers or in the query |
| `expiresAt` | <pre>int</pre>  | Token expiration date, in Epoch-millis (UTC)                                             |
| `ttl`       | <pre>int</pre>  | Token time to live, in milliseconds                                                      |

Once `auth:login` has been called, the returned authentication token is stored by the SDK and used for all the subsequent API call, ensuring they are properly authenticated.

## Usage

<<< ./snippets/login.java
