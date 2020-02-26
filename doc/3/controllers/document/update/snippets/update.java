    ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<>();
    content.put("name", "Johny");

    ConcurrentHashMap<String, Object> response =
    kuzzle.getDocumentController().update("nyc-open-data", "yellow-taxi", "some-id", content)
    .get();
    System.out.println(response);