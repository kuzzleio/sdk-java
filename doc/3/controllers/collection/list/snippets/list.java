  ConcurrentHashMap<String, Object> result = kuzzle
      .getCollectionController()
      .list("nyc-open-data")
      .get();

/*
    {
      size=10,
      collections=[
        {
          name=dark-taxi,
          type=stored
        },
        {
          name=pink-taxi,
          type=stored
        }
       ],
      from=0,
      type=all
     }
 */