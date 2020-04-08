package io.kuzzle.sdk.CoreClasses;

import com.google.gson.internal.LazilyParsedNumber;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarOutputStream;

public class SearchResult {

  private SearchOptions options;
  private ConcurrentHashMap<String, Object> request;
  private String scrollAction = "scroll";
  private Kuzzle kuzzle;

  public ConcurrentHashMap<String, Object> aggregations;
  public ArrayList<ConcurrentHashMap<String, Object>> hits;
  public Integer total;
  public Integer fetched;
  public String scrollId;

  public SearchResult(
      Kuzzle kuzzle,
      ConcurrentHashMap<String, Object> request,
      SearchOptions options,
      Response response) {

    ConcurrentHashMap<String, Object> _response = response.toMap();

    this.kuzzle = kuzzle;
    this.options = options;
    this.request = request;

    this.aggregations = (ConcurrentHashMap<String, Object>) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("aggregations");
    this.hits = (ArrayList<ConcurrentHashMap<String, Object>>) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("hits");
    this.total = ((LazilyParsedNumber) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("total")).intValue();
    this.fetched = hits.size();
    this.scrollId = (String) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("scrollId");
  }

  public SearchResult(
      Kuzzle kuzzle,
      ConcurrentHashMap<String, Object> request,
      SearchOptions options,
      Response response,
      Integer previouslyFetched) {

    ConcurrentHashMap<String, Object> _response = response.toMap();

    this.kuzzle = kuzzle;
    this.options = options;
    this.request = request;

    this.aggregations = (ConcurrentHashMap<String, Object>) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("aggregations");
    this.hits = (ArrayList<ConcurrentHashMap<String, Object>>) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("hits");
    this.total = ((LazilyParsedNumber) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("total")).intValue();
    this.fetched = hits.size() + previouslyFetched;
    this.scrollId = (String) ((ConcurrentHashMap<String, Object>) _response.get("result")).get("scrollId");
  }

  private ConcurrentHashMap<String, Object> getScrollRequest() {
    final ConcurrentHashMap<String, Object> obj = new ConcurrentHashMap<>();

    obj.put("controller", this.request.get("controller"));
    obj.put("action", this.scrollAction);
    obj.put("scrollId", this.scrollId);
    return obj;
  }

  private ConcurrentHashMap<String, Object> getSearchAfterRequest() {
    ConcurrentHashMap<String, Object> nextRequest = this.request;
    ConcurrentHashMap<String, Object> lastItem = this.hits.get(this.hits.size());
    ArrayList<Object> searchAfter = new ArrayList<>();
    ArrayList<Object> sort = (ArrayList<Object>) ((ConcurrentHashMap<String, Object>) this.request.get("body")).get("sort");

    ((ConcurrentHashMap<String, Object>) this.request.get("body")).put("search_after", searchAfter);

    for (Object value : sort) {
      String key;

      if (value instanceof String) {
        key = value.toString();
      } else {
        key = ((ConcurrentHashMap<String, Object>) value).get("First").toString();
      }

      if (key.equals("_uid")) {
        searchAfter.add(this.request.get("collection").toString() + "#" + lastItem.get("_id").toString());
      } else {
        ConcurrentHashMap<String, Object> _source = (ConcurrentHashMap<String, Object>) lastItem.get("_source");
        searchAfter.add(_source.get(key));
      }
    }
    return nextRequest;
  }

  public SearchResult next() throws NotConnectedException, InternalException, ExecutionException, InterruptedException {
    if (this.fetched >= this.total) return null;

    ConcurrentHashMap<String, Object> nextRequest = new ConcurrentHashMap<>();
    if (this.scrollId != null) {
      nextRequest = this.getScrollRequest();
    } else if (this.options.getSize() != null
        && ((ConcurrentHashMap<String, Object>) this.request.get("body")).get("sort") != null) {
      nextRequest = this.getSearchAfterRequest();
    } else if (this.options.getSize() != null) {
      if (this.options.getFrom() != null && this.options.getFrom() > this.total) {
        return null;
      }

      this.options.setFrom(this.fetched);
      nextRequest = this.request;
    }

    if (nextRequest == null) {
      return null;
    }

    Response response = this.kuzzle.query(nextRequest).get();
    return new SearchResult(this.kuzzle, nextRequest, this.options, response, this.fetched);
  }
}
