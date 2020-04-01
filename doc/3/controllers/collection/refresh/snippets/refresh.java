    kuzzle
        .getCollectionController()
        .refresh("nyc-open-data", "yellow-taxi")
        .get();