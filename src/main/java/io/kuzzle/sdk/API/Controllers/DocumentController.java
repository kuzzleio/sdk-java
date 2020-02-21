package io.kuzzle.sdk.API.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
   * Gets a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param queuable
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> get(
      final String index,
      final String collection,
      final String id,
      final Boolean queuable) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "get")
        .put("_id",  id)
        .put("queuable", queuable);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Gets a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> get(
      final String index,
      final String collection,
      final String id) throws NotConnectedException, InternalException {

    return this.get(index, collection, id, false);
  }
}
