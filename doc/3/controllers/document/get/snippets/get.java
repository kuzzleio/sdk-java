ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "John");

    kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", document, "some-id")
    .get();

    ConcurrentHashMap<String, Object> response =
    kuzzle.getDocumentController().get("nyc-open-data", "yellow-taxi", "some-id")
    .get();