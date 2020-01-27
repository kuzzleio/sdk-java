ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();

ConcurrentHashMap<String, Object> custom = new ConcurrentHashMap<>();
custom.put("age", 42);

ConcurrentHashMap<String, Object> result = kuzzle.getAuthController().updateSelf(custom).get();
