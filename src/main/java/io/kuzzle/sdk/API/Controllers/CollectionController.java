package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.concurrent.CompletableFuture;

public class CollectionController extends BaseController {

  public CollectionController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Tells if a collection exists in a given index.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> exists(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "exists");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (Boolean) response.result);
  }
}
