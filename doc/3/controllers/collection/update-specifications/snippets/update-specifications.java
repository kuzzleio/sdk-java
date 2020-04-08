    ConcurrentHashMap<String, Object> specifications = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> fields = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "string");
    fields.put("license", license);
    specifications.put("fields", fields);

    ConcurrentHashMap<String, Object> result = kuzzle
        .getCollectionController()
        .updateSpecifications("nyc-open-data", "yellow-taxi", specifications)
        .get();

/*
   {
    strict=false,
    fields={
      license={
        mandatory=true,
        type="string"
      }
        }
    }
 */
