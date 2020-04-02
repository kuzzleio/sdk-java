Boolean result = kuzzle
    .getCollectionController()
    .exists("nyc-open-data", "yellow-taxi")
    .get();
