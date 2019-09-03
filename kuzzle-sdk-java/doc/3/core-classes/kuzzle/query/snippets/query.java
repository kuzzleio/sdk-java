
JsonObject query = new JsonObject();

query.addProperty("controller", "server");
query.addProperty("action", "now");

try {
    Response<JsonObject> response = kuzzle.query(query).get();
    System.out.println(response);

} catch(KuzzleException e) {
    e.printStackTrace();
}