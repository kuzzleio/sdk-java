# How to contribute to the Java SDK

Here are a few rules and guidelines to follow if you want to contribute to the Java SDK and, more importantly, if you want to see your pull requests accepted by Kuzzle team.

## Tools

We use git submodules to link the sdk-go, sdk-c and sdk-cpp.  
When you are developing a new functionality that had implications on the other SDK, you should align all your submodules on your development branch.  
You can use `align-submodules.sh` script to achieve this. (Eg: `./align-submodules.sh 1-dev` to align all submodules on `1-dev` branch)


To build the SDK, you can use this Docker image to build the SDK:  
```
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make clean all"
```

## Running Tests

We are using cucumber for the tests.  
You will need a Kuzzle stack to run the tests.  

```
# Start a Kuzzle stack
./.ci/start_kuzzle.sh

# Build the SDK
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make clean all"

# Run tests
docker run --rm -it -e ARCH="amd64" --network ci_default --link kuzzle -v "$(pwd)":/mnt kuzzleio/sdk-cross:openjdk8 bash -c "make test"
```
