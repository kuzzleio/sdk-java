package io.kuzzle.test.CoreClasses.TaskTest;

import com.google.gson.internal.LazilyParsedNumber;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.CoreClasses.SearchResult;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Options.SearchOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.spy;

public class SearchResultTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  private Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
  private SearchOptions options = new SearchOptions();
  private ConcurrentHashMap<String, Object> request = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, Object> aggregations = new ConcurrentHashMap<>();
  private ArrayList<ConcurrentHashMap<String, Object>> hits = new ArrayList<>();
  private Response response = new Response();

  @Test
  public void constructorTestA() {
    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollId", "");
    result.put("total", new LazilyParsedNumber("1"));

    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzleMock, request, options, response);
    Assert.assertNotNull(searchResult);
  }

  @Test
  public void constructorTestB() {
    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollId", "");
    result.put("total", new LazilyParsedNumber("1"));

    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzleMock, request, options, response, 10);
    Assert.assertNotNull(searchResult);
  }

  @Test
  public void nextTestA() throws InternalException, ExecutionException, NotConnectedException, InterruptedException {
    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollId", "30s");
    result.put("scrollAction", "scroll");
    result.put("total", new LazilyParsedNumber("10"));
    request.put("controller", "document");
    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzleMock, request, options, response, 10);
    searchResult = searchResult.next();
    Assert.assertNull(searchResult);
  }

  @Test
  public void nextTestB() throws InternalException, ExecutionException, NotConnectedException, InterruptedException {
    result.put("aggregations", aggregations);
    result.put("hits", hits);
    result.put("scrollAction", "scroll");
    result.put("total", new LazilyParsedNumber("20"));
    request.put("controller", "document");
    options.setSize(10);
    options.setFrom(30);
    ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();
    request.put("body", body);
    response.result = result;

    SearchResult searchResult = new SearchResult(kuzzleMock, request, options, response, 10);
    searchResult = searchResult.next();
    Assert.assertNull(searchResult);
  }
}
