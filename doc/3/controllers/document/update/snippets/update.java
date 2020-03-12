    ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<>();
    content.put("name", "Johny");

    ConcurrentHashMap<String, Object> result =
    kuzzle.getDocumentController().update("nyc-open-data", "yellow-taxi", "some-id", content)
    .get();

/*
    response =
    {
        _id=some-id,
        _version=2
    }
*/