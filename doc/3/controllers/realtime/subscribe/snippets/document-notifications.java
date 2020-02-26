SubscribeOptions options = new SubscribeOptions();
options.setSubscribeToSelf(true);

ConcurrentHashMap<String, Object> filters = new ConcurrentHashMap<>();
filters.put("exists", "name");

ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
document.put("name", "nina-vkote");

kuzzle.getRealtimeController().subscribe(
"nyc-open-data",
"yellow-taxi",
filters,
notification -> {
  if (notification.scope.equals(SubscribeOptions.Scope.IN.toString())) {
    System.out.println("Document entered the scope");
  } else {
    System.out.println("Document left the scope");
  }
}).get();

ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
query.put("controller", "document");
query.put("action", "create");
query.put("index", "nyc-open-data");
query.put("collection", "yellow-taxi");
query.put("_id", "nina-vkote");
query.put("body", document);
kuzzle.query(query).get();