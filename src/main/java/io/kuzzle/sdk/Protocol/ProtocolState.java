package io.kuzzle.sdk.Protocol;

public enum ProtocolState {
  CLOSE, // The network protocol does not accept requests.
  OPEN, // The network protocol accepts new requests.
}
