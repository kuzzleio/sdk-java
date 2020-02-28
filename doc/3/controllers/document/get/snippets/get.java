    ConcurrentHashMap<String, Object> result =
    kuzzle.getDocumentController().get("nyc-open-data", "yellow-taxi", "some-id")
    .get();

/*
        {
          _source=
            {
              key1=value1,
              key2=value2,
              _kuzzle_info= { createdAt=1582892127577, author=-1}
            },
          _id=some-id,
          _version=1
        }
*/
