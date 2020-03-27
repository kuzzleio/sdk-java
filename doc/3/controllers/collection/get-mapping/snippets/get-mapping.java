ConcurrentHashMap<String, Object> result = kuzzle
    .getCollectionController()
    .getMapping("nyc-open-data", "test")
    .get();

/*
{
  _meta={
      schema={},
      allowForm=false
    },
    dynamic=true,
    properties={
      key={
        type=text,
          fields={
            keyword={
            ignore_above=256,
            type=keyword
            }
          }
        }
      }
    }
*/