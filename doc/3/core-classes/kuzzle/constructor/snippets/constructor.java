KuzzleOptions options = new KuzzleOptions();
options.setMaxQueueSize(42)
        .setMaxRequestDelay(1)
        .setMinTokenDuration(24)
        .setRefreshedTokenDuration(40)
        .setQueueFilter(stringObjectConcurrentHashMap -> true);
Kuzzle kuzzle = new Kuzzle(new WebSocket("kuzzle"), options);
