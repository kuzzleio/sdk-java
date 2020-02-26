package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class IndexController extends BaseController {
  public IndexController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Creates a new index in Kuzzle via the persistence engine.
   *
   * @param index
   * @return A CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> create(final String index) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "index")
            .put("action", "create")
            .put("index", index))
        .thenApplyAsync((response) -> null);
  }

  /**
   * Deletes an index in Kuzzle via the persistence engine.
   *
   * @param index
   * @return A CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> delete(final String index) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "index")
            .put("action", "delete")
            .put("index", index))
        .thenApplyAsync((response) -> null);
  }

  /**
   * Checks if an index exists in the Kuzzle persistence engine.
   *
   * @param index
   * @return A CompletableFuture<Boolean>
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> exists(final String index) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "index")
            .put("action", "exists")
            .put("index", index))
        .thenApplyAsync((response) -> (Boolean) response.result);
  }

  /**
   * Lists indexes from the Kuzzle persistence engine.
   *
   * @return A CompletableFuture<ArrayList<String>>
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ArrayList<String>> list() throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "index")
            .put("action", "list"))
        .thenApplyAsync((response) -> (((KuzzleMap) response.result).getArrayList("indexes")));
  }

  /**
   * Deletes multiple indexes from the Kuzzle persistence engine.
   *
   * @param indexes
   * @return A CompletableFuture<ArrayList<String>>
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ArrayList<String>> mDelete(final ArrayList<String> indexes) throws NotConnectedException, InternalException {
    return kuzzle
        .query(new KuzzleMap()
            .put("controller", "index")
            .put("action", "mDelete")
            .put("body", new KuzzleMap().put("indexes", indexes)))
        .thenApplyAsync((response) -> (((KuzzleMap) response.result).getArrayList("deleted")));
  }
}
