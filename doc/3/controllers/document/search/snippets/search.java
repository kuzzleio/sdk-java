    ConcurrentHashMap<String, Object> suv = new ConcurrentHashMap<>();
    suv.put("category", "suv");
    ConcurrentHashMap<String, Object> limousine = new ConcurrentHashMap<>();
    limousine.put("category", "limousine");

    CreateOptions options = new CreateOptions();
    options.setWaitForRefresh(true);

    for (int i = 0; i < 5; i += 1) {
      kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", suv, options).get();
    }

    for (int i = 0; i < 10; i += 1) {
      kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", limousine, options).get();
    }

    ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> match = new ConcurrentHashMap<>();
    match.put("category", "suv");
    query.put("match", match);
    searchQuery.put("query", query);

    SearchResult results = kuzzle
        .getDocumentController()
        .search("nyc-open-data", "yellow-taxi", searchQuery).get();

  /*
    {
      "aggregations"=undefined,
      "hits"=[
        {
          "_id"="AWgi6A1POQUM6ucJ3q06",
          "_score"=0.046520017,
          "_source"={
            "category"="suv",
            "_kuzzle_info"={
              "author"="-1",
              "createdAt"=1546773859655,
              "updatedAt"=null,
              "updater"=null
            }
          }
        },
        ...
      ]
    },
    "total"=5,
    "fetched"=5,
    "scroll_id"=undefined
  */
    System.out.println("Successfully retrieved " + results.total + " documents");