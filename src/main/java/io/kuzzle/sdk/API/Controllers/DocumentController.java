package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentController extends BaseController {
  public DocumentController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Replace a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> replace(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "replace")
        .put("body", document)
        .put("_id",  id)
        .put("waitForRefresh", waitForRefresh);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Replace a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> replace(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    return this.replace(index, collection, id, document, false);
  }

}
