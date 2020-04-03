    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
    match.put("capacity", 4);
    query.put("match", match);
    searchQuery.put("query", query);
    ArrayList<String> result = kuzzle
      .getDocumentController()
      .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery)
      .get();
