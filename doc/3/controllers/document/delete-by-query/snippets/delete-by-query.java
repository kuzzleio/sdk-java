    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
    match.put("capacity", 4);
    searchQuery.put("match", match);
    ArrayList<String> result = kuzzle.getDocumentController().deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get();