[![Build Status](https://travis-ci.org/kuzzleio/sdk-java.svg?branch=master)](https://travis-ci.org/kuzzleio/sdk-java)

Official Kuzzle JAVA SDK
======

## About Kuzzle

A backend software, self-hostable and ready to use to power modern apps.

You can access the Kuzzle repository on [Github](https://github.com/kuzzleio/kuzzle)

## SDK Documentation

The complete SDK documentation is available [here](https://docs.kuzzle.io/sdk/java/2)

## Protocol used

The JAVA SDK implements the websocket protocol.

### Build jar

Execute the following snippet to clone the GIT repository, and build the SDK. It will then be available in the "build/" directory

```sh
git clone git@github.com:kuzzleio/sdk-java.git
cd sdk-java
./gradlew jar
```

## Installation

### Bintray repository

The SDK is available for both x86 and amd64 architectures on bintray:

https://bintray.com/kuzzle/maven

### Maven

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>kuzzle-sdk-java</artifactId>
  <version>2.0.0</version>
  <type>pom</type>
</dependency>
```


### Gradle

```groovy
compile 'io.kuzzle:kuzzle-sdk-java:2.0.0'
```

For amd64:

```groovy
compile 'io.kuzzle:kuzzle-sdk-java:2.0.0'
```
