


    ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();

    body.put("key1", "value1");

    document1.put("_id", "some-id1");
    document1.put("body", body);

    document2.put("_id", "some-id2");
    document2.put("body", body);

    final ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    documents.add(document1);
    documents.add(document2);

    ConcurrentHashMap<String, ArrayList<Object>> response = kuzzle.getDocumentController().mCreate("nyc-open-data", "yellow-taxi", documents)
    .get();

    System.out.println("Successfully created " + response.get("successes").size() + " documents");
