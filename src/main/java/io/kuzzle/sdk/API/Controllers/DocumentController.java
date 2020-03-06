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
//   //   * Creates or replaces multiple documents in a given collection and index.
//   //   *
//   //   * @param index
//   //   * @param collection
//   //   * @param documents
//   //   * @param waitForRefresh
//   //   * @return a CompletableFuture
//   //   * @throws NotConnectedException
//   //   * @throws InternalException
//   //   */
//  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreateOrReplace(
//      final String index,
//      final String collection,
//      final ArrayList<ConcurrentHashMap<String, Object>> documents,
//      final Boolean waitForRefresh) throws NotConnectedException, InternalException {
//
//    final KuzzleMap query = new KuzzleMap();
//    query
//        .put("index", index)
//        .put("collection", collection)
//        .put("controller", "document")
//        .put("action", "mCreateOrReplace")
//        .put("body", new KuzzleMap().put("documents", documents))
//        .put("waitForRefresh", waitForRefresh);
//
//    return kuzzle
//        .query(query)
//        .thenApplyAsync(
//            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
//  }
//
//  /**
//   * Creates or replaces multiple documents in a given collection and index.
//   *
//   * @param index
//   * @param collection
//   * @param documents
//   * @return a CompletableFuture
//   * @throws NotConnectedException
//   * @throws InternalException
//   */
//  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreateOrReplace(
//      final String index,
//      final String collection,
//      final ArrayList<ConcurrentHashMap<String, Object>> documents) throws NotConnectedException, InternalException {
//
//    return this.mCreateOrReplace(index, collection, documents, null);
//  }

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
     final DocumentOptions options) throws NotConnectedException, InternalException {

   final KuzzleMap query = new KuzzleMap();

   String id = null;
   Boolean waitForRefresh = null;
   if (options != null) {
     waitForRefresh = options.getWaitForRefresh();
     id = options.getId();
   }

   query
       .put("index", index)
       .put("collection", collection)
       .put("controller", "document")
       .put("action", "create")
       .put("body", document)
       .put("_id",  id)
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
  * @return a CompletableFuture
  * @throws NotConnectedException
  * @throws InternalException
  */
 public CompletableFuture<ConcurrentHashMap<String, Object>> create(
     final String index,
     final String collection,
     final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

   return this.create(index, collection, document, null);
 }

  /**
   * Deletes multiple documents.
   *
   * @param index
   * @param collection
   * @param ids
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
      final String index,
      final String collection,
      final ArrayList<String> ids,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {


    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mDelete")
        .put("waitForRefresh", waitForRefresh)
        .put("body", new KuzzleMap().put("ids", ids));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }

  /**
   * Deletes multiple documents.
   *
   * @param index
   * @param collection
   * @param ids
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
      final String index,
      final String collection,
      final ArrayList<String> ids) throws NotConnectedException, InternalException {

    return mDelete(index, collection, ids, null);
  }

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
