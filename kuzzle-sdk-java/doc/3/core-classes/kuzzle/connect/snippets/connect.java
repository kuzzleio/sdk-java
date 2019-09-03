
WebSocket networkProtocol = new WebSocket("localhost");

Kuzzle kuzzle = new Kuzzle(networkProtocol);

kuzzle.connect();