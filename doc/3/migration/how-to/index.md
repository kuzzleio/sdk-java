---
code: false
type: page
title: SDK Java to SDK Jvm
description: SDK Java to SDK Jvm
order: 99
---

# SDK Jvm

In this tutorial you will learn how to migrate from the Kuzzle **Java SDK** to the **Jvm SDK**.

::: info
Having trouble? Get in touch with us on [Discord](http://join.discord.kuzzle.io)!
:::

## Installation

The installation of the SDK is the same as the Java SDK.
You can find the SDK JARs directly on [bintray](https://bintray.com/kuzzle/maven/sdk-jvm). Download and add it to your classpath.

### Maven:
**from**
```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>kuzzle-sdk-java</artifactId>
  <version>3.0.0</version>
  <type>pom</type>
</dependency>
```
**to**
```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>sdk-jvm</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Gradle:
**from**
```groovy
implementation 'io.kuzzle:kuzzle-sdk-java:3.0.0'
```
**to**
```groovy
implementation 'io.kuzzle:sdk-jvm:1.0.0'
```

### Ivy:
**from**
```html
<dependency org='io.kuzzle' name='kuzzle-sdk-java' rev='3.0.0'>
  <artifact name='kuzzle-sdk-java' ext='pom' ></artifact>
</dependency>
```
**to**
```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.0.0'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```

# Breaking changes

## Options handling

The main difference between those two SDKs is the way how we handle options.

In the Java SDK, we have an `Options` class for each option type such as [SearchOptions](/sdk/java/3/core-classes/search-options),
[CreateOptions](/sdk/java/3/core-classes/create-options) or [SubscriptionOptions](/sdk/java/3/core-classes/subscribe-options).

It allowed us to not write as much as overloads as available options.
If the method signature could have 5 options, we had to have 5 different signatures,
or one with the options object, and another without it.

Since the new Jvm SDK is written in Kotlin, we now use the optional parameters directly in
the function signature, and add a `@JvmOverload` annotation to it in order to generate
as much as overload as we need.

That is why in this SDK, **we do not have any `Options` object**.

For example:

```java
public CompletableFuture<SearchResult> search(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final SearchOptions options)
```
Becomes:

```kotlin
@JvmOverloads
fun search(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      scroll: String? = null,
      from: Int = 0,
      size: Int? = null): CompletableFuture<SearchResult>
```

Which will generate the followings signatures:

```java
public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer from,
      Integer size)

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer from)

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll)

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery)
```

### Example

Using the Java SDK, you could have written:

```java
SearchOptions options = new SearchOptions();
    options.setFrom(1);
    options.setSize(5);

SearchResult results = kuzzle.getDocumentController().search(
  "nyc-open-data",
  "yellow-taxi",
  searchQuery, options).get();
```

With the new Jvm SDK it becomes:

```java
SearchResult results = kuzzle.getDocumentController().search(
  "nyc-open-data",
  "yellow-taxi",
  searchQuery, null, 1, 5).get();
```

::: info
You can find the full documentation of the Jvm SDK [here](https://docs.kuzzle.io/sdk/jvm/1), and compare signatures to adapt your code.
:::