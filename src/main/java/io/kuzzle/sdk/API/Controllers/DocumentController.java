package io.kuzzle.sdk.API.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.ArrayList;
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
   * @param _id
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document,
      final String _id,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {
    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "create")
        .put("_id", _id)
        .put("body", document)
        .put("waitForRefresh", waitForRefresh);

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
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
          final String index,
          final String collection,
          final ConcurrentHashMap<String, Object> document,
          final Boolean waitForRefresh) throws NotConnectedException, InternalException {
    return this.create(index, collection, document, UUID.randomUUID().toString(), waitForRefresh);
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
    return this.create(index, collection, document, UUID.randomUUID().toString(), false);
  }
  /**
   * Creates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param document
   * @param _id
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
          final String index,
          final String collection,
          final ConcurrentHashMap<String, Object> document,
          final String _id) throws NotConnectedException, InternalException {
    return this.create(index, collection, document, _id, false);
  }

}
