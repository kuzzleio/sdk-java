[![Build Status](https://travis-ci.org/kuzzleio/sdk-java.svg?branch=master)](https://travis-ci.org/kuzzleio/sdk-java) [![codecov.io](http://codecov.io/github/kuzzleio/sdk-java/coverage.svg?branch=master)](http://codecov.io/github/kuzzleio/sdk-java?branch=master)

Official Kuzzle C SDK
======

## About Kuzzle

A backend software, self-hostable and ready to use to power modern apps.

You can access the Kuzzle repository on [Github](https://github.com/kuzzleio/kuzzle)

## SDK Documentation

The complete SDK documentation is available [here](http://docs.kuzzle.io/sdk-reference/)

## Protocol used

The JAVA SDK implements the websocket protocol.

## Build

Execute the following snippet to clone the GIT repository, and build the SDK. It will then be available in the "build/" directory

```sh
git clone --recursive git@github.com:kuzzleio/sdk-java.git
cd sdk-java
git submodule update --init --recursive
make
```

## Installation

### Bintray repository

The SDK is available for both x86 and amd64 architectures on bintray:

https://bintray.com/kuzzle/maven

### Maven

For x86:

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>kuzzle-sdk-java-x86</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

For amd64:

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>kuzzle-sdk-java-amd64</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

For x86:

```groovy
compile 'io.kuzzle:kuzzle-sdk-java-x86:1.0.0'
```

For amd64:

```groovy
compile 'io.kuzzle:kuzzle-sdk-java-amd64:1.0.0'
```