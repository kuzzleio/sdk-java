SubscribeOptions options = new SubscribeOptions();
options.setSubscribeToSelf(true);
options.setScope(SubscribeOptions.Scope.ALL);

ConcurrentHashMap<String, Object> filters = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> range = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> age = new ConcurrentHashMap<>();

age.put("lte", 20);
range.put("age", age);
filters.put("range", range);

kuzzle.getRealtimeController().subscribe(
  "nyc-open-data",
  "yellow-taxi",
  filters,
  notification -> {
    if (notification.scope.equals(SubscribeOptions.Scope.OUT.toString())) {
      System.out.println("Document left the scope");
    } else {
      System.out.println("Document moved in the scope");
    }
  }, options).get();

ConcurrentHashMap document = new ConcurrentHashMap<>();
document.put("age", 19);
ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
query.put("controller", "document");
query.put("action", "create");
query.put("index", "nyc-open-data");
query.put("collection", "yellow-taxi");
query.put("_id", "nina-vkote");
query.put("body", document);
kuzzle.query(query).get();

query.put("action", "update");
document.put("age", 42);
kuzzle.query(query).get();