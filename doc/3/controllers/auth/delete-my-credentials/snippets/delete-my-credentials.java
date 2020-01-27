ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

ConcurrentHashMap<String, Object> response = kuzzle.getAuthController().login("local", credentials).get();
kuzzle.getAuthController().deleteMyCredentials("local").get();
