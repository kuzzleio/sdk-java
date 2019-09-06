FROM openjdk:8

LABEL io.kuzzle.vendor="Kuzzle <support@kuzzle.io>"
LABEL description="Runner for java documentation snippets"
LABEL name="kuzzleio/documentation:java"

RUN apt-get update && apt-get install -y \
      git \
      curl

RUN curl https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.5/gson-2.8.5.jar --output gson2.8.5.jar

WORKDIR /app