    final ArrayList<String> ids = new ArrayList<>();
    ids.add("some-id");
    ids.add("some-id2");

    ConcurrentHashMap<String, ArrayList<Object>> result = kuzzle.getDocumentController().mDelete("nyc-open-data", "yellow-taxi", ids)
    .get();
/*
    result =
        {
            successes=[some-id, some-id2],
            errors=[]
        }

*/