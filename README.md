[![Build Status](https://travis-ci.org/kuzzleio/sdk-java.svg?branch=master)](https://travis-ci.org/kuzzleio/sdk-java) [![codecov.io](http://codecov.io/github/kuzzleio/sdk-java/coverage.svg?branch=master)](http://codecov.io/github/kuzzleio/sdk-java?branch=master)

Official Kuzzle C SDK
======

## About Kuzzle

A backend software, self-hostable and ready to use to power modern apps.

You can access the Kuzzle repository on [Github](https://github.com/kuzzleio/kuzzle)

## SDK Documentation

The complete SDK documentation is available [here](http://docs.kuzzle.io/sdk-reference/)

## Protocol used

The JAVA SDK implement the websocket protocol.

## Installation

### Clone the depot

```sh
git clone --recursive git@github.com:kuzzleio/sdk-java.git
git submodule update --init --recursive
```

### Build

```
make
```

You should know have the SDK in the `build/java/build/libs` directory.