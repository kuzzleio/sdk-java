Boolean result = kuzzle
  .getDocumentController()
  .exists("nyc-open-data", "yellow-taxi", "some-id")
  .get();
