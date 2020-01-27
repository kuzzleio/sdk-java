ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
  credentials.put("username", "foo");
  credentials.put("password", "bar");

ConcurrentHashMap<String, Object> result = kuzzle.getAuthController().login("local", credentials).get();
