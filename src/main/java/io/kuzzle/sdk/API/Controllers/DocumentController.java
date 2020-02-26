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
   * Updates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document,
      final ConcurrentHashMap<String, Object> options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    final KuzzleMap _options = KuzzleMap
        .from(options);

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "update")
        .put("body", document)
        .put("_id",  id)
        .put("waitForRefresh", _options != null ? _options.getBoolean("waitForRefresh") : null)
        .put("retryOnConflict", _options != null ? _options.getNumber("retryOnConflict") : 0)
        .put("source", _options != null ? _options.getBoolean("source") : null);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Updates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    return this.update(index, collection, id, document, null);
  }
}
