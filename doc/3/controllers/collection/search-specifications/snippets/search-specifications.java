
ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> filters = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> args = new ConcurrentHashMap<>();

filters.put("match_all", args);
searchQuery.put("query", filters);

SearchOptions options = new SearchOptions();
options.setSize(50);
options.setFrom(0);
options.setScroll("10s");

SearchResult result = kuzzle
    .getCollectionController()
    .searchSpecifications(searchQuery, options).get();

System.out.println("fetched: " + result.fetched);
  /*
    {
      "total"=1,
      "hits"=[
        {
          "_index"="%kuzzle",
          "_type"="validations",
          "_id"="nyc-open-data#yellow-taxi",
          "_score"=1,
          "_source"={
            "validation"={
              "strict"=false,
              "fields"={
                "license"={
                  "type"="string"
                }
              }
            },
            "index"="nyc-open-data",
            "collection"="yellow-taxi"
          }
        }
      ],
      "scrollId"="DnF1ZXJ5VGhlbkZldGNoBQAAAAAAAACSFlgtZTJFYjNiU1FxQzhSNUFpNlZHZGcAAAAAAAAAkxZYLWUyRWIzYlNRcUM4UjVBaTZWR2RnAAAAAAAAAJQWWC1lMkViM2JTUXFDOFI1QWk2VkdkZwAAAAAAAACVFlgtZTJFYjNiU1FxQzhSNUFpNlZHZGcAAAAAAAAAlhZYLWUyRWIzYlNRcUM4UjVBaTZWR2Rn"
    }
  */