  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("key", "value");

  Boolean result = kuzzle
  .getDocumentController()
  .validate("nyc-open-data", "yellow-taxi", document)
  .get();
