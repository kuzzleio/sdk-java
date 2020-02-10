package io.kuzzle.test.API.Controllers;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.Events.Event;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {
  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  @Test
  public void checkTokenTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().checkToken("my-token");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "checkToken");
    assertEquals("my-token", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("token").toString());
  }

  @Test
  public void createMyCredentialsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().createMyCredentials("local", credentials);
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "createMyCredentials");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void credentialsExistTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().credentialsExist("local");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "credentialsExist");
    assertEquals("local", ((((KuzzleMap) arg.getValue()).getString("strategy"))));
  }

  @Test
  public void getMyCredentialsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().getMyCredentials("local");
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "getMyCredentials");
    assertEquals("local", ((((KuzzleMap) arg.getValue()).getString("strategy"))));
  }

  @Test
  public void getMyRightsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().getMyRights();
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "getMyRights");
  }

  @Test
  public void getStrategies() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().getStrategies();
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "getStrategies");
  }

  @Test
  public void loginSuccessTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    CompletableFuture<Response> future = new CompletableFuture<>();
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);
    Response response = new Response();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();

    result.put("jwt", "my-token");
    result.put("_id", "my-id");
    response.result = result;

    doReturn(future).when(kuzzleSpy).query(any(KuzzleMap.class));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    kuzzleSpy.getAuthController().login("local", credentials);
    future.complete(response);
    Thread.sleep(1000);
    verify(kuzzleSpy).setAuthenticationToken(eq("my-token"));
    verify(kuzzleSpy).trigger(eq(Event.loginAttempt), eq(true));
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "login");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals(((KuzzleMap) arg.getValue()).getString("expiresIn"), "1h");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void loginSuccessWithExpiresInTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    CompletableFuture<Response> future = new CompletableFuture<>();
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);
    Response response = new Response();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();

    result.put("jwt", "my-token");
    result.put("_id", "my-id");
    response.result = result;

    doReturn(future).when(kuzzleSpy).query(any(KuzzleMap.class));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    kuzzleSpy.getAuthController().login("local", credentials, "42h");
    future.complete(response);
    Thread.sleep(1000);
    verify(kuzzleSpy).setAuthenticationToken(eq("my-token"));
    verify(kuzzleSpy).trigger(eq(Event.loginAttempt), eq(true));
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "login");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals(((KuzzleMap) arg.getValue()).getString("expiresIn"), "42h");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void loginFailTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    CompletableFuture<Response> future = new CompletableFuture<>();
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);
    Response response = new Response();
    response.result = new ConcurrentHashMap<>();

    doReturn(future).when(kuzzleSpy).query(any(KuzzleMap.class));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    kuzzleSpy.getAuthController().login("local", credentials);
    future.complete(response);
    Thread.sleep(1000);
    verify(kuzzleSpy).setAuthenticationToken(eq(null));
    verify(kuzzleSpy).trigger(eq(Event.loginAttempt), eq(false));
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "login");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals(((KuzzleMap) arg.getValue()).getString("expiresIn"), "1h");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void refreshTokenTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    CompletableFuture<Response> future = new CompletableFuture<>();
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);
    Response response = new Response();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();

    result.put("jwt", "my-token");
    response.result = result;

    doReturn(future).when(kuzzleSpy).query(any(KuzzleMap.class));

    kuzzleSpy.getAuthController().refreshToken();
    future.complete(response);
    Thread.sleep(1000);
    verify(kuzzleSpy).setAuthenticationToken(eq("my-token"));
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "refreshToken");
    assertEquals(((KuzzleMap) arg.getValue()).getString("expiresIn"), "1h");
  }

  @Test
  public void refreshTokenWithExpiresInTest() throws NotConnectedException, InternalException, InterruptedException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));
    CompletableFuture<Response> future = new CompletableFuture<>();
    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);
    Response response = new Response();
    ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();

    result.put("jwt", "my-token");
    response.result = result;

    doReturn(future).when(kuzzleSpy).query(any(KuzzleMap.class));

    kuzzleSpy.getAuthController().refreshToken("42h");
    future.complete(response);
    Thread.sleep(1000);
    verify(kuzzleSpy).setAuthenticationToken(eq("my-token"));
    verify(kuzzleSpy).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "refreshToken");
    assertEquals(((KuzzleMap) arg.getValue()).getString("expiresIn"), "42h");
  }

  @Test
  public void updateMyCredentialsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().updateMyCredentials("local", credentials);
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "updateMyCredentials");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void updateSelfTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().updateSelf(credentials);
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "updateSelf");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }

  @Test
  public void validateMyCredentialsTest() throws NotConnectedException, InternalException {
    Kuzzle kuzzleSpy = spy(new Kuzzle(networkProtocol));

    ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
    credentials.put("username", "foo");
    credentials.put("password", "foobar");

    ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

    kuzzleSpy.getAuthController().validateMyCredentials("local", credentials);
    verify(kuzzleSpy, times(1)).query((KuzzleMap) arg.capture());

    assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "auth");
    assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "validateMyCredentials");
    assertEquals(((KuzzleMap) arg.getValue()).getString("strategy"), "local");
    assertEquals("foo", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("username"));
    assertEquals("foobar", ((ConcurrentHashMap<String, Object>)(((KuzzleMap) arg.getValue()).get("body"))).get("password"));
  }
}
