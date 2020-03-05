package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.DocumentOptions;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentController extends BaseController {
  public DocumentController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

// /**
//  * Updates a document in a given collection and index.
//  *
//  * @param index
//  * @param collection
//  * @param id
//  * @param document
//  * @param options
//  * @return a CompletableFuture
//  * @throws NotConnectedException
//  * @throws InternalException
//  */
// public CompletableFuture<ConcurrentHashMap<String, Object>> update(
//     final String index,
//     final String collection,
//     final String id,
//     final ConcurrentHashMap<String, Object> document,
//     final DocumentOptions options) throws NotConnectedException, InternalException {
//
//   final KuzzleMap query = new KuzzleMap();
//   Integer retryOnConflict = null;
//   Boolean waitForRefresh = null;
//   Boolean source = null;
//   if (options != null) {
//     retryOnConflict = options.getRetryOnConflict();
//     source = options.getSource();
//     waitForRefresh = options.getWaitForRefresh();
//   }
//
//   query
//       .put("index", index)
//       .put("collection", collection)
//       .put("controller", "document")
//       .put("action", "update")
//       .put("body", document)
//       .put("_id",  id)
//       .put("waitForRefresh", waitForRefresh)
//       .put("retryOnConflict", retryOnConflict)
//       .put("source", source);
//
//   return kuzzle
//       .query(query)
//       .thenApplyAsync(
//           (response) -> (ConcurrentHashMap<String, Object>) response.result);
// }
//
// /**
//  * Updates a document in a given collection and index.
//  *
//  * @param index
//  * @param collection
//  * @param id
//  * @param document
//  * @return a CompletableFuture
//  * @throws NotConnectedException
//  * @throws InternalException
//  */
// public CompletableFuture<ConcurrentHashMap<String, Object>> update(
//     final String index,
//     final String collection,
//     final String id,
//     final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {
//
//   return this.update(index, collection, id, document, null);
// }

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

    return this.replace(index, collection, id, document, null);
  }

  /**
   * Deletes a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> delete(
      final String index,
      final String collection,
      final String id,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "delete")
        .put("_id",  id)
        .put("waitForRefresh", waitForRefresh);

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

    return this.delete(index, collection, id, null);
  }

  /**
   * Gets multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param ids
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mGet(
      final String index,
      final String collection,
      final ArrayList<String> ids) throws NotConnectedException, InternalException {


    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mGet")
        .put("body", new KuzzleMap().put("ids", ids));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }
}
