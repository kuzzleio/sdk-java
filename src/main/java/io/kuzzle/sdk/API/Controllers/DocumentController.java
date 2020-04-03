package io.kuzzle.sdk.API.Controllers;

import com.google.gson.internal.LazilyParsedNumber;
import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.CoreClasses.SearchResult;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;
import io.kuzzle.sdk.Options.UpdateOptions;
import io.kuzzle.sdk.Options.CreateOptions;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

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
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document,
      final CreateOptions options) throws NotConnectedException, InternalException {

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
        .put("_id", id)
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
   * Updates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document,
      final UpdateOptions options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    Integer retryOnConflict = null;
    Boolean waitForRefresh = null;
    Boolean source = null;
    if (options != null) {
      retryOnConflict = options.getRetryOnConflict();
      source = options.getSource();
      waitForRefresh = options.getWaitForRefresh();
    }

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "update")
        .put("body", document)
        .put("_id", id)
        .put("waitForRefresh", waitForRefresh)
        .put("retryOnConflict", retryOnConflict)
        .put("source", source);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Updates a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> update(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    return this.update(index, collection, id, document, null);
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

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "get")
        .put("_id", id);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Creates or Replace a document in a given collection and index.
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
  public CompletableFuture<ConcurrentHashMap<String, Object>> createOrReplace(
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
        .put("action", "createOrReplace")
        .put("body", document)
        .put("_id", id)
        .put("waitForRefresh", waitForRefresh);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Creates or Replace a document in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> createOrReplace(
      final String index,
      final String collection,
      final String id,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    return this.createOrReplace(index, collection, id, document, null);
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
        .put("_id", id)
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
        .put("_id", id)
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

  /**
   * Replaces multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mReplace(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mReplace")
        .put("body", new KuzzleMap().put("documents", documents))
        .put("waitForRefresh", waitForRefresh);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }

  /**
   * Replaces multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mReplace(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents) throws NotConnectedException, InternalException {

    return this.mReplace(index, collection, documents, null);
  }

  /**
   * Tells if a document exists in a given collection and index.
   *
   * @param index
   * @param collection
   * @param id
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> exists(
      final String index,
      final String collection,
      final String id) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "exists")
        .put("_id", id);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (Boolean) response.result);
  }

  /**
   * Updates multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mUpdate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents,
      final UpdateOptions options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    Integer retryOnConflict = null;
    Boolean waitForRefresh = null;
    if (options != null) {
      retryOnConflict = options.getRetryOnConflict();
      waitForRefresh = options.getWaitForRefresh();
    }
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mUpdate")
        .put("body", new KuzzleMap().put("documents", documents))
        .put("retryOnConflict", retryOnConflict)
        .put("waitForRefresh", waitForRefresh);


    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }

  /**
   * Updates multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mUpdate(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents) throws NotConnectedException, InternalException {

    return this.mUpdate(index, collection, documents, null);
  }

  /**
   * Creates or replaces multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @param waitForRefresh
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreateOrReplace(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents,
      final Boolean waitForRefresh) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "mCreateOrReplace")
        .put("body", new KuzzleMap().put("documents", documents))
        .put("waitForRefresh", waitForRefresh);

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, ArrayList<Object>>) response.result);
  }

  /**
   * Creates or replaces multiple documents in a given collection and index.
   *
   * @param index
   * @param collection
   * @param documents
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mCreateOrReplace(
      final String index,
      final String collection,
      final ArrayList<ConcurrentHashMap<String, Object>> documents) throws NotConnectedException, InternalException {

    return this.mCreateOrReplace(index, collection, documents, null);
  }

  /**
   * Validates data against existing validation rules.
   *
   * @param index
   * @param collection
   * @param document
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> validate(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> document) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "validate")
        .put("body", new KuzzleMap(document));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (Boolean) ((ConcurrentHashMap<String, Object>) response.result).get("valid"));
  }

  /**
   * Counts documents in a collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Integer> count(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("body", new KuzzleMap().put("query", searchQuery))
        .put("action", "count");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> ((LazilyParsedNumber) ((ConcurrentHashMap<String, Object>) response.result).get("count")).intValue());
  }

  /**
   * Counts documents in a collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Integer> count(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();

    return this.count(index, collection, searchQuery);
  }

  /**
   * Searches documents.
   *
   * @param index
   * @param collection
   * @param searchQuery
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<SearchResult> search(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery,
      final SearchOptions options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "document")
        .put("action", "search")
        .put("body", new KuzzleMap(searchQuery));

    if (options != null) {
      query
          .put("from", options.getFrom())
          .put("size", options.getSize());
      if (options.getScroll() != null) {
        query.put("scroll", options.getScroll());
      }
    }

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> new SearchResult(kuzzle, query, options, response));
  }

  /**
   * Searches documents.
   *
   * @param index
   * @param collection
   * @param searchQuery
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<SearchResult> search(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException {

    return this.search(index, collection, searchQuery, new SearchOptions());
  }
}
