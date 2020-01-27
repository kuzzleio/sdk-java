ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();
Boolean result = kuzzle.getAuthController().validateMyCredentials("local", credentials).get();
