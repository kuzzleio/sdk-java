
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");
  document.put("lastname", "Smith");

  kuzzle.getDocumentController().replace("nyc-open-data", "yellow-taxi", "some-id", document)
  .get();
