    final ArrayList<String> ids = new ArrayList<>();
    ids.add("some-id");
    ids.add("some-id2");

    ConcurrentHashMap<String, ArrayList<Object>> result = kuzzle.getDocumentController().mGet("nyc-open-data", "yellow-taxi", ids)
    .get();

/*
    result =
        {
            successes=
            [
                {
                    result=created,
                    _source=
                        {
                            key=value,
                            _kuzzle_info={createdAt=1582892842099, author=-1}
                        },
                    _id=some-id,
                    _version=1
                },
                {
                    result=created,
                    _source=
                        {
                            key=value,
                            _kuzzle_info={createdAt=1582892842099, author=-1}
                        },
                    _id=some-id2,
                    _version=1
                }
            ],
            errors=[]
        }

*/