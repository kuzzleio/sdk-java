# How to contribute to the Java SDK

Here are a few rules and guidelines to follow if you want to contribute to the Java SDK and, more importantly, if you want to see your pull requests accepted by the  Kuzzle team.

## Tools

This SDK inherits from the following repositories, linked as git submodules: sdk-go, sdk-c and sdk-cpp.  
Whenever significant changes are applied to the parent SDKs, you need to align the linked submodules accordingly.
You can use the `align-submodules.sh` script to achieve this. (e.g. `./align-submodules.sh 1-dev` to align all submodules on `1-dev` branch)


You can use this Docker image to build the SDK:  
```
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make clean all"
```

You can build the SDK for different architecture by specifying the `ARCH` environment variable to the container.  
The following architecture are available: `amd64` and `x86`.

## Running Tests

Tests are handled by [cucumber](https://cucumber.io/).  
You will need to start a Kuzzle stack to run the tests: 

```
# Start a Kuzzle stack
./.ci/start_kuzzle.sh

# Build the SDK
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make clean all"

# Run tests
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make test"
```
