ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
query.put("controller", "document");
query.put("action", "create");
query.put("index", "nyc-open-data");
query.put("collection", "yellow-taxi");
query.put("_id", "my-custom-document-id");
query.put("refresh", "wait_for");
ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();
body.put("trip_distance", 4.23);
body.put("passenger_count", 2);
query.put("body", body);

Response res = kuzzle.query(query).get();
