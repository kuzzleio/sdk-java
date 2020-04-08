package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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

  /**
   * Removes all documents from collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> truncate(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "truncate");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (null));
  }

  /**
   * Creates a collection in a given index.
   *
   * @param index
   * @param collection
   * @param mappings
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> mappings) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "create")
        .put("body", mappings != null ? new KuzzleMap(mappings) : null);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> null);
  }

  /**
   * Creates a collection in a given index.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> create(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    return this.create(index, collection, null);
  }

  /**
   * Get collection mapping
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> getMapping(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "getMapping");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Refreshes collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> refresh(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "refresh");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> null);
  }
}
