package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class DocumentController extends BaseController {
  public DocumentController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Deletes a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> delete(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    final KuzzleMap _options = KuzzleMap
        .from(options);

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "delete")
        .put("_id",  id)
        .put("queuable", _options.getBoolean("queuable"))
        .put("waitForRefresh", _options.getBoolean("waitForRefresh"));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Deletes a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> delete(
      final String index,
      final String collection,
      final String id) throws NotConnectedException, InternalException {

    final ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();
    return this.delete(index, collection, id, options);
  }

}
