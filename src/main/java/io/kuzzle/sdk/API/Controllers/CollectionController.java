package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.ListOptions;

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
  * List collections.
  *
  * @param index
  * @param options
  * @return a CompletableFuture
  * @throws NotConnectedException
  * @throws InternalException
  */
 public CompletableFuture<ConcurrentHashMap<String, Object>> list(
     final String index,
     final ListOptions options) throws NotConnectedException, InternalException {

   final KuzzleMap query = new KuzzleMap();
   query
       .put("index", index)
       .put("controller", "collection")
       .put("action", "list");

   if (options != null) {
     query
         .put("from", options.getFrom())
         .put("size", options.getSize());
   }

   return kuzzle
       .query(query)
       .thenApplyAsync(
           (response) -> (ConcurrentHashMap<String, Object>) response.result);
 }

 public CompletableFuture<ConcurrentHashMap<String, Object>> list(
     final String index) throws NotConnectedException, InternalException {

   return this.list(index, null);
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
}
