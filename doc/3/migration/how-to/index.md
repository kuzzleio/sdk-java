---
code: false
type: page
title: SDK Java to SDK JVM
description: SDK Java to SDK JVM
order: 99
---

# SDK JVM

In this tutorial you will learn how to migrate from the Kuzzle **Java SDK** to the **JVM SDK**.

::: info
Having trouble? Get in touch with us on [Discord](http://join.discord.kuzzle.io)!
:::

## Installation

The installation of the JVM SDK is the same as for the Java SDK.
You can find the SDK JARs directly on [bintray](https://bintray.com/kuzzle/maven/sdk-jvm). Download them and add them to your classpath.

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

The main difference between those two SDKs is the way they handle options on API actions.

In the Java SDK, there is an option class for each API action, such as [SearchOptions](/sdk/java/3/core-classes/search-options),
[CreateOptions](/sdk/java/3/core-classes/create-options) or [SubscriptionOptions](/sdk/java/3/core-classes/subscribe-options).

Using option objects was a simple and easy way for our SDK users to handle the many available options in API actions.

The new JVM SDK is now entirely written in Kotlin. The Java part of that SDK is now generated from the Kotlin code.  
The idiomatic way of handling optional arguments in Kotlin is to use named arguments, which is not supported by Java. 

What this means is this: optional arguments in Java are now handled using overloads. Most of them are automatically created when converting Kotlin code to Java, but we also added a few ones of our own, and removed less pertinent ones, so that the methods exposed by the Java SDK are as consistent as possible.

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
      size: Int? = null,
      from: Int = 0): CompletableFuture<SearchResult>
```

Which will generate the followings signatures:

```java
public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery)

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll)

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer size)
      
public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      Integer size,
      Integer from)
```

### Example

Using the Java SDK, you could have written:

```java
SearchOptions options = new SearchOptions();
    options.setScroll("10s");
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
  searchQuery, "10s", 5).get();
```

::: info
You can find the full documentation of the Jvm SDK [here](https://docs.kuzzle.io/sdk/jvm/1), and compare signatures to adapt your code.
:::
