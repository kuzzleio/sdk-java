    ConcurrentHashMap<String, Object> response =
    kuzzle.getDocumentController().get("nyc-open-data", "yellow-taxi", "some-id")
    .get();
    System.out.println(response);