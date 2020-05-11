ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();
Boolean exists = kuzzle.getAuthController().credentialsExist("local").get();
if (exists) {
  System.out.println("Credentials exists for local strategy");
}
