package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.SearchResult;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionController extends BaseController {

  public CollectionController(final Kuzzle kuzzle) {
    super(kuzzle);
  }

  /**
   * Tells if a collection exists in a given index.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Boolean> exists(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "exists");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (Boolean) response.result);
  }

  /**
   * Removes all documents from collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> truncate(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "truncate");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (null));
  }

  /**
   * Creates a collection in a given index.
   *
   * @param index
   * @param collection
   * @param mappings
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> mappings) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "create")
        .put("body", mappings != null ? new KuzzleMap(mappings) : null);

    return kuzzle
        .query(query)
        .thenApplyAsync((response) -> null);
  }

  /**
   * Creates a collection in a given index.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> create(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    return this.create(index, collection, null);
  }

  /**
   * Get collection mapping
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> getMapping(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "getMapping");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Updates collection mapping.
   *
   * @param index
   * @param collection
   * @param mapping
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> update(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> mapping) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "update")
        .put("body", new KuzzleMap(mapping));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> null);
  }

  /**
   * Refreshes collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> refresh(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "refresh");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> null);
  }

  /**
   * Deletes the validation specifications associated to the given index and collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<Void> deleteSpecifications(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "deleteSpecifications");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> null);
  }

  /**
   * Gets the validation specifications associated to the given index and collection.
   *
   * @param index
   * @param collection
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> getSpecifications(
      final String index,
      final String collection) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "getSpecifications");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Validates specifications for a collection.
   *
   * @param index
   * @param collection
   * @param specifications
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> validateSpecifications(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> specifications) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "validateSpecifications")
        .put("body", new KuzzleMap(specifications));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Updates validation specifications for a collection.
   *
   * @param index
   * @param collection
   * @param specifications
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> updateSpecifications(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> specifications) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();

    query
        .put("index", index)
        .put("collection", collection)
        .put("controller", "collection")
        .put("action", "updateSpecifications")
        .put("body", new KuzzleMap(specifications));

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }

  /**
   * Searches collection specifications.
   *
   * @param searchQuery
   * @param options
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<SearchResult> searchSpecifications(
      final ConcurrentHashMap<String, Object> searchQuery,
      final SearchOptions options) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("controller", "collection")
        .put("action", "searchSpecifications")
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
   * Searches collection specifications.
   *
   * @param searchQuery
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<SearchResult> searchSpecifications(
      final ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException {

    return this.searchSpecifications(searchQuery, null);
  }

  /**
   * List collections.
   *
   * @param index
   * @return a CompletableFuture
   * @throws NotConnectedException
   * @throws InternalException
   */
  public CompletableFuture<ConcurrentHashMap<String, Object>> list(
      final String index) throws NotConnectedException, InternalException {

    final KuzzleMap query = new KuzzleMap();
    query
        .put("index", index)
        .put("controller", "collection")
        .put("action", "list");

    return kuzzle
        .query(query)
        .thenApplyAsync(
            (response) -> (ConcurrentHashMap<String, Object>) response.result);
  }
}
