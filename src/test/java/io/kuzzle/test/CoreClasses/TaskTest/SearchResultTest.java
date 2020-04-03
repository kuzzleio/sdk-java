package io.kuzzle.test.CoreClasses.TaskTest;

import com.google.gson.internal.LazilyParsedNumber;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.CoreClasses.SearchResult;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class SearchResultTest {

  private Kuzzle kuzzle = Mockito.mock(Kuzzle.class);
  private SearchOptions options = Mockito.mock(SearchOptions.class);

  @Test
  public void constructorTestA() {
    ConcurrentHashMap<String, Object> request = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> aggregations = new ConcurrentHashMap<>();
    ArrayList<ConcurrentHashMap<String, Object>> hits = new ArrayList<>();

    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollId", "");
    result.put("total", new LazilyParsedNumber("1"));

    Response response = new Response();
    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzle, request, options, response);
    Assert.assertNotNull(searchResult);

  }

  @Test
  public void constructorTestB() {
    ConcurrentHashMap<String, Object> request = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> aggregations = new ConcurrentHashMap<>();
    ArrayList<ConcurrentHashMap<String, Object>> hits = new ArrayList<>();

    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollId", "");
    result.put("total", new LazilyParsedNumber("1"));

    Response response = new Response();
    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzle, request, options, response, 10);
    Assert.assertNotNull(searchResult);

  }
}
