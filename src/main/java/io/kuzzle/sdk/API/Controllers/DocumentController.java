package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentController extends BaseController {
  public DocumentController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Creates multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {


    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mCreate")
        .put("body", new KuzzleMap().put("documents", documents))
        .put("waitForRefresh", waitForRefresh);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }

  /**
   * Creates multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents) throws NotConnectedException, InternalException {

    return this.mCreate(index, collection, documents, null);
  }
}
