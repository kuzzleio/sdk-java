    ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();

    document1.put("name", "Yoann");
    document2.put("nickname", "El angel de la muerte que hace el JAVA");

    ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();

    documents.add(document1);
    documents.add(document2);

  kuzzle.getDocumentController().mCreate("nyc-open-data", "yellow-taxi", documents)
  .get();
