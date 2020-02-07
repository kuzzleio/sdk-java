
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");
  document.put("lastname", "Smith");

  ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

  kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", document, "some-id", options)
  .get();
