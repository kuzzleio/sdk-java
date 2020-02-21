    ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();

    document1.put("_id", "some-id1");
    body1.put("key1", "value1");
    document1.put("body", body);

    document2.put("_id", "some-id2");
    body2.put("key2", "value2");
    document2.put("body", body);

    final ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    kuzzle.getDocumentController().mCreate("nyc-open-data", "yellow-taxi", documents)
    .get();
