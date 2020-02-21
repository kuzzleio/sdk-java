
    ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
    document.put("name", "John");

    kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", document, "some-id")
    .get();

    ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<>();
    content.put("name", "Johny");

    ConcurrentHashMap<String, Object> response =
    kuzzle.getDocumentController().update("nyc-open-data", "yellow-taxi", "some-id", content)
    .get();