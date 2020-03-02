package io.kuzzle.test.API.Controllers.DocumentTest;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.AbstractProtocol;
import io.kuzzle.sdk.Protocol.ProtocolState;
import io.kuzzle.sdk.Protocol.WebSocket;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  // @Test
  // public void mDeleteDocumentTestA() throws NotConnectedException, InternalException {

  //   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   final ArrayList<String> ids = new ArrayList<>();
  //   ids.add("some-id1");
  //   ids.add("some-id2");
  //   ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

  //   kuzzleMock.getDocumentController().mDelete(index, collection, ids);
  //   Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

  //   assertEquals((arg.getValue()).getString("controller"), "document");
  //   assertEquals((arg.getValue()).getString("action"), "mDelete");
  //   assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
  //   assertEquals((arg.getValue()).getBoolean("waitForRefresh"), null);
  //   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(0), "some-id1");
  //   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(1), "some-id2");
  // }

  // @Test
  // public void mDeleteDocumentTestB() throws NotConnectedException, InternalException {

  //   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   final ArrayList<String> ids = new ArrayList<>();
  //   ids.add("some-id1");
  //   ids.add("some-id2");
  //   ArgumentCaptor<KuzzleMap> arg = ArgumentCaptor.forClass(KuzzleMap.class);

  //   kuzzleMock.getDocumentController().mDelete(index, collection, ids, false);
  //   Mockito.verify(kuzzleMock, Mockito.times(1)).query(arg.capture());

  //   assertEquals((arg.getValue()).getString("controller"), "document");
  //   assertEquals((arg.getValue()).getString("action"), "mDelete");
  //   assertEquals((arg.getValue()).getString("index"), "nyc-open-data");
  //   assertEquals((arg.getValue()).getBoolean("waitForRefresh"), false);
  //   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(0), "some-id1");
  //   assertEquals(((ArrayList<String>)(((KuzzleMap)(arg.getValue()).get("body"))).get("ids")).get(1), "some-id2");
  // }

  // @Test(expected = NotConnectedException.class)
  // public void mDeleteDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
  //   AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
  //   Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

  //   Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   final ArrayList<String> ids = new ArrayList<>();
  //   ids.add("some-id1");
  //   ids.add("some-id2");

  //   kuzzleMock.getDocumentController().mDelete(index, collection, ids);
  // }

}
