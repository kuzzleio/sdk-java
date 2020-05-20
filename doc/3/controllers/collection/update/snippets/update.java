    ConcurrentHashMap<String, Object> mapping = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

    license.put("type", "keyword");
    properties.put("license", license);
    mapping.put("properties", properties);

    kuzzle
        .getCollectionController()
        .update("nyc-open-data", "yellow-taxi", mapping)
        .get();