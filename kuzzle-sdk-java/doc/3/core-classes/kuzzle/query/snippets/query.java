
JsonObject query = new JsonObject();

query.addProperty("controller", "server");
query.addProperty("action", "now");

try {
    Response<JsonObject> response = kuzzle.query(query).get();
    System.out.println(response);
    /*
        {
            "room":"66dc8c4e-5377-41c3-950c-2afa475b28ee",
            "result":{
                "now":1567778067734
            },
            "error":null,
            "requestId":"66dc8c4e-5377-41c3-950c-2afa475b28ee",
            "status":200,
            "controller":"server",
            "action":"now",
            "index":null,
            "collection":null,
            "volatile":{
                "sdkVersion":"2.0",
                "sdkInstanceId":"2ab9e8bd-0946-42fe-aa93-808954f210a2"
            },
            "protocol":null,
            "scope":null,
            "timestamp":null,
            "type":null
        }
     */
} catch(KuzzleException e) {
    e.printStackTrace();
}