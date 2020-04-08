    ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
    ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();

    body.put("category", "suv");

    for (int i = 0; i < 100; i++) {
      ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
      document.put("_id", "suv_no" + i);
      document.put("body", body);
      documents.add(document);
    }

    kuzzle
      .getDocumentController()
      .mCreate("nyc-open-data", "yellow-taxi", documents, true).get();

    SearchOptions options = new SearchOptions();
    options.setScroll("10s");
    options.setSize(10);

    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
    match.put("category", "suv");
    query.put("match", match);
    searchQuery.put("query", query);

    SearchResult results = kuzzle.getDocumentController().search(
      "nyc-open-data",
      "yellow-taxi",
      searchQuery, options).get();

    // Fetch the matched items by advancing through the result pages
    ArrayList<ConcurrentHashMap<String, Object>> matched = new ArrayList<>();

    while (results != null) {
      matched.addAll(results.hits);
      results = results.next();
    }

  /*
    { _id="suv_no1",
      _score=0.03390155,
      _source=
       { _kuzzle_info=
          { author="-1",
            updater=null,
            updatedAt=null,
            createdAt=1570093133057 },
         category="suv" } }
  */
    System.out.println("Successfully retrieved " + matched.size() + " documents");