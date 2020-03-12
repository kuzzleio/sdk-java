package io.kuzzle.sdk.CoreClasses;

import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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

    this.aggregations = (ConcurrentHashMap<String, Object>)((ConcurrentHashMap<String, Object>)_response.get("result")).get("aggregations");
    this.hits = (ArrayList<ConcurrentHashMap<String, Object>>)((ConcurrentHashMap<String, Object>)_response.get("result")).get("hits");
    this.total = (Integer)((ConcurrentHashMap<String, Object>)_response.get("result")).get("total");
    this.fetched = hits.size();
    this.scrollId = (String)((ConcurrentHashMap<String, Object>)_response.get("result")).get("scrollId");
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

    this.aggregations = (ConcurrentHashMap<String, Object>)((ConcurrentHashMap<String, Object>)_response.get("result")).get("aggregations");
    this.hits = (ArrayList<ConcurrentHashMap<String, Object>>)((ConcurrentHashMap<String, Object>)_response.get("result")).get("hits");
    this.total = (Integer)((ConcurrentHashMap<String, Object>)_response.get("result")).get("total");
    this.fetched = hits.size() + previouslyFetched;
    this.scrollId = (String)((ConcurrentHashMap<String, Object>)_response.get("result")).get("scrollId");
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


    //this.request.put(this.request.get("body").get("sort"), searchAfter);
    return null;
  }

  private SearchResult next() throws NotConnectedException, InternalException {
    if (this.fetched >= this.total) return null;

    ConcurrentHashMap<String, Object> nextRequest = new ConcurrentHashMap<>();

    if (this.scrollId != null) {
      nextRequest = this.getScrollRequest();
    }

    else if (options.getSize() != null
            && ((ConcurrentHashMap<String,Object>)this.request.get("body")).get("sort") != null) {
      nextRequest = this.getSearchAfterRequest();
    }

    else if (options.getSize() != null) {
      if (options.getFrom() != null && options.getFrom() > this.total) {
        return null;
      }
      options.setFrom(this.fetched);
      nextRequest = this.request;
    }

    if (nextRequest == null) {
      return null;
    }

    Response response = this.kuzzle.query(nextRequest);

    return new SearchResult(this.kuzzle, nextRequest, this.options, response, this.fetched);
  }
}
