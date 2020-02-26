
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");
  document.put("lastname", "Smith");

  kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", document)
  .get();
