
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");

  ConcurrentHashMap<String, Object> result = kuzzle
    .getDocumentController()
    .createOrReplace("nyc-open-data", "yellow-taxi", "some-id", document)
    .get();

/*
    {
      _source=
        {
          firstname=John,
          _kuzzle_info={ 
            createdAt=1582892323254, 
            author=-1, 
            updatedAt=1582892323254, 
            updater=-1
          }
        },
    _id=some-id,
    _version=1
    }
 */
