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

  // /**
  //  * Deletes multiple documents.
  //  *
  //  * @param index
  //  * @param collection
  //  * @param ids
  //  * @param waitForRefresh
  //  * @return a CompletableFuture
  //  * @throws NotConnectedException
  //  * @throws InternalException
  //  */
  // public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
  //     final String index,
  //     final String collection,
  //     final ArrayList<String> ids,
  //     final Boolean waitForRefresh) throws NotConnectedException, InternalException {


  //   final KuzzleMap query = new KuzzleMap();
  //   query
  //       .put("index", index)
  //       .put("collection", collection)
  //       .put("controller", "document")
  //       .put("action", "mDelete")
  //       .put("waitForRefresh", waitForRefresh)
  //       .put("body", new KuzzleMap().put("ids", ids));

  //   return kuzzle
  //       .query(query)
  //       .thenApplyAsync(
  //           (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  // }

  // /**
  //  * Deletes multiple documents.
  //  *
  //  * @param index
  //  * @param collection
  //  * @param ids
  //  * @return a CompletableFuture
  //  * @throws NotConnectedException
  //  * @throws InternalException
  //  */
  // public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
  //     final String index,
  //     final String collection,
  //     final ArrayList<String> ids) throws NotConnectedException, InternalException {

  //   return mDelete(index, collection, ids, null);
  // }
}
