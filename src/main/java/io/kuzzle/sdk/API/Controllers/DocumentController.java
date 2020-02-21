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
   * Creates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param document
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document,
      final ConcurrentHashMap<String, Object> options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    final KuzzleMap _options = KuzzleMap
        .from(options);

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "create")
        .put("body", document)
        .put("_id",  _options.getString("_id"))
        .put("waitForRefresh", _options.getBoolean("waitForRefresh"));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Creates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    final ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();
    return this.create(index, collection, document, options);
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
        .put("waitForRefresh", _options.getBoolean("waitForRefresh"))
        .put("queuable", _options.getBoolean("queuable"))
        .put("retryOnConflict", _options.getNumber("retryOnConflict") != null ? _options.getNumber("retryOnConflict") : 0)
        .put("source", _options.getBoolean("source"));

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

    final ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();
    return this.update(index, collection, id, document, options);
  }
}