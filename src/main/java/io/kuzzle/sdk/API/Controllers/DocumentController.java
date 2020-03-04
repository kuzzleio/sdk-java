package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.DocumentOptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentController extends BaseController {
  public DocumentController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

//  /**
//   * Updates a document in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param id
//   * @param document
//   * @param options
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
//      final String index,
//      final String collection,
//      final String id,
//      final ConcurrentHashMap<String, Object> document,
//      final DocumentOptions options) throws NotConnectedException, InternalException {
//
//    final KuzzleMap query = new KuzzleMap();
//    Integer retryOnConflict = null;
//    Boolean waitForRefresh = null;
//    Boolean source = null;
//    if (options != null) {
//      retryOnConflict = options.getRetryOnConflict();
//      source = options.getSource();
//      waitForRefresh = options.getWaitForRefresh();
//    }
//
//    query
//        .put("index", index)
//        .put("collection", collection)
//        .put("controller", "document")
//        .put("action", "update")
//        .put("body", document)
//        .put("_id",  id)
//        .put("waitForRefresh", waitForRefresh)
//        .put("retryOnConflict", retryOnConflict)
//        .put("source", source);
//
//    return kuzzle
//        .query(query)
//        .thenApplyAsync(
//            (response) -> (ConcurrentHashMap<String, Object>) response.result);
//  }
//
//  /**
//   * Updates a document in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param id
//   * @param document
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
//      final String index,
//      final String collection,
//      final String id,
//      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {
//
//    return this.update(index, collection, id, document, null);
//  }
}
