ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();
ConcurrentHashMap<String, Object> result = 
  kuzzle.getAuthController().refreshToken("1h").get();
