
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");
  document.put("lastname", "Smith");

  ConcurrentHashMap<String, Object> response = kuzzle.getDocumentController().createOrReplace("nyc-open-data", "yellow-taxi", "some-id", document)
  .get();
  System.out.println(response);