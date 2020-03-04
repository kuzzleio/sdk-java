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

//  /**
//   * Creates a document in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param document
//   * @param options
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
//      final String index,
//      final String collection,
//      final ConcurrentHashMap<String, Object> document,
//      final DocumentOptions options) throws NotConnectedException, InternalException {
//
//    final KuzzleMap query = new KuzzleMap();
//
//    String id = null;
//    Boolean waitForRefresh = null;
//    if (options != null) {
//      waitForRefresh = options.getWaitForRefresh();
//      id = options.getId();
//    }
//
//    query
//        .put("index", index)
//        .put("collection", collection)
//        .put("controller", "document")
//        .put("action", "create")
//        .put("body", document)
//        .put("_id",  id)
//        .put("waitForRefresh", waitForRefresh);
//
//    return kuzzle
//        .query(query)
//        .thenApplyAsync(
//            (response) -> (ConcurrentHashMap<String, Object>) response.result);
//  }
//
//  /**
//   * Creates a document in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param document
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
//      final String index,
//      final String collection,
//      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {
//
//    return this.create(index, collection, document, null);
//  }
//
//  /**
//   * Gets multiple documents in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param ids
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mGet(
//      final String index,
//      final String collection,
//      final ArrayList<String> ids) throws NotConnectedException, InternalException {
//
//
//    final KuzzleMap query = new KuzzleMap();
//    query
//        .put("index", index)
//        .put("collection", collection)
//        .put("controller", "document")
//        .put("action", "mGet")
//        .put("body", new KuzzleMap().put("ids", ids));
//
//    return kuzzle
//        .query(query)
//        .thenApplyAsync(
//            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
//  }
}
