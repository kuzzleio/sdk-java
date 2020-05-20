import io.kuzzle.sdk.*;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Protocol.WebSocket;

import java.util.concurrent.ConcurrentHashMap;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] args) {

    try {
      // Creates a WebSocket connection.
      // Replace "kuzzle" with
      // your Kuzzle hostname like "localhost"
      WebSocketOptions opts = new WebSocketOptions();
      opts.setAutoReconnect(true).setConnectionTimeout(42000);

      WebSocket ws = new WebSocket("kuzzle", opts);

      // Instantiates a Kuzzle client
      kuzzle = new Kuzzle(ws, (KuzzleOptions) null);

      // Connects to the server.
      kuzzle.connect();
      System.out.println("Connected!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // New document content
    ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<>();
    content.put("name", "John");
    content.put("birthday", "1995-11-27");
    content.put("license", "B");

    // Stores the document in the "yellow-taxi" collection.
    try {
      kuzzle.getDocumentController()
          .create( "nyc-open-data", "yellow-taxi", content).get();
      System.out.println("New document added to the yellow-taxi collection!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // Disconnects the SDK.
    kuzzle.disconnect();
  }
}