    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
    match.put("Hello", "Clarisse");
    searchQuery.put("match", match);
    Integer result = kuzzle
                      .getDocumentController()
                      .count("nyc-open-data", "yellow-taxi", searchQuery)
                      .get();