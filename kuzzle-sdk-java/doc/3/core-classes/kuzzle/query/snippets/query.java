
ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<String, Object>();

query.put("controller", "server");
query.put("action", "now");

try {
    Response response = kuzzle.query(query).get();
    System.out.println(response.toMap());
    /*
        {
            result={
                now=1568631977992
            },
            controller=server,
            requestId=241a1d6a-fe76-44c6-8440-f4b6a49d7a22,
            action=now,
            volatile={
                sdkInstanceId=33c41a1d-ac3f-4661-a161-8a585ca4acd8,
                sdkVersion=2.0
            },
            room=241a1d6a-fe76-44c6-8440-f4b6a49d7a22,
            status=200
        }
     */
    System.out.println("Success");
} catch(Exception e) {
    e.printStackTrace();
}