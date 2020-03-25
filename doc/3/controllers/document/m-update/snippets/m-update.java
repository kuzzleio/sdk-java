ConcurrentHashMap<String, Object> document1 = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> body2 = new ConcurrentHashMap<>();

body.put("name", "Smith");
body2.put("name", "Freeman");

document1.put("_id", "some-id");
document1.put("body", body);

document2.put("_id", "some-id2");
document2.put("body", body2);

final ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<>();
documents.add(document1);
documents.add(document2);

ConcurrentHashMap<String, ArrayList<Object>> result = kuzzle
  .getDocumentController()
  .mUpdate("nyc-open-data", "yellow-taxi", documents)
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
                        Agent=Smith,
                        _kuzzle_info={createdAt=1582892842099, author=-1}
                    },
                _id=some-id,
                _version=1,
                status=200
            },
            {
                result=created,
                _source=
                    {
                        Gordon=Freeman,
                        _kuzzle_info={createdAt=1582892842099, author=-1}
                    },
                _id=some-id2,
                _version=1,
                status=200
            }
        ],
        errors=[]
    }
*/
