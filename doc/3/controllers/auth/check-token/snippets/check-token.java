ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

ConcurrentHashMap<String, Object> response = kuzzle.getAuthController().login("local", credentials).get();
ConcurrentHashMap<String, Object> responseToken = kuzzle.getAuthController().checkToken(response.get("jwt").toString()).get();
