    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> fields = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "symbol"); // type 'symbol' is not a recognized type
    fields.put("license", license);
    specifications.put("fields", fields);

    ConcurrentHashMap<String, Object> result = kuzzle
        .getCollectionController()
        .validateSpecifications("nyc-open-data", "yellow-taxi", specifications)
        .get();

/*
  {
    valid=false,
    details=[
      "In nyc-open-data.yellow-taxi.license: symbol is not a recognized type."
    ],
    description="Some errors with provided specifications."
  }
*/