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

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentTest {

  private AbstractProtocol networkProtocol = Mockito.mock(WebSocket.class);

  // @Test
  // public void deleteDocumentTestA() throws NotConnectedException, InternalException {

  //   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

  //   kuzzleMock.getDocumentController().delete(index, collection, "some-id", true);
  //   Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

  //   assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "delete");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
  //   assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), true);
  // }

  // @Test
  // public void deleteDocumentTestB() throws NotConnectedException, InternalException {

  //   Kuzzle kuzzleMock = spy(new Kuzzle(networkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   ArgumentCaptor arg = ArgumentCaptor.forClass(KuzzleMap.class);

  //   kuzzleMock.getDocumentController().delete(index, collection, "some-id");
  //   Mockito.verify(kuzzleMock, Mockito.times(1)).query((KuzzleMap) arg.capture());

  //   assertEquals(((KuzzleMap) arg.getValue()).getString("controller"), "document");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("action"), "delete");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("index"), "nyc-open-data");
  //   assertEquals(((KuzzleMap) arg.getValue()).getString("_id"), "some-id");
  //   assertEquals(((KuzzleMap) arg.getValue()).getBoolean("waitForRefresh"), null);
  // }

  // @Test(expected = NotConnectedException.class)
  // public void deleteDocumentShouldThrowWhenNotConnected() throws NotConnectedException, InternalException {
  //   AbstractProtocol fakeNetworkProtocol = Mockito.mock(WebSocket.class);
  //   Mockito.when(fakeNetworkProtocol.getState()).thenAnswer((Answer<ProtocolState>) invocation -> ProtocolState.CLOSE);

  //   Kuzzle kuzzleMock = spy(new Kuzzle(fakeNetworkProtocol));
  //   String index = "nyc-open-data";
  //   String collection = "yellow-taxi";

  //   kuzzleMock.getDocumentController().delete(index, collection, "some-id");
  // }
}
