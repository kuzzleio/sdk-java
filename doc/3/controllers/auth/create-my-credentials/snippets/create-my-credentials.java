ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

ConcurrentHashMap<String, Object> newCredentials = new ConcurrentHashMap<>();
newCredentials.put("username", "foo2");
newCredentials.put("password", "bar2");

ConcurrentHashMap<String, Object> response = 
  kuzzle.getAuthController().login("local", credentials).get();
kuzzle.getAuthController().createMyCredentials("other", newCredentials).get();
