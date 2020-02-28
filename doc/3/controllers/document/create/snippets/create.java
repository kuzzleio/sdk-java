
  ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
  document.put("firstname", "John");

  ConcurrentHashMap<String, Object> result = kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", document)
  .get();

/* result =
        {
          _source=
              {
              firstname=John,
              _kuzzle_info={ createdAt=1582891678488, author=-1 }
              },
          _id=mDmyi3ABYMAjFJ_ZO-EZ,
          _version=1
        }
*/