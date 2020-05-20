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
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Subscribes to notifications for drivers having a "B" driver license.
    ConcurrentHashMap<String, Object> filters = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Object> equals = new ConcurrentHashMap<>();
    equals.put("license", "B");
    filters.put("equals", equals);

    try {
      // Sends the subscription
      kuzzle.getRealtimeController()
          .subscribe("nyc-open-data", "yellow-taxi", filters, notification -> {
            ConcurrentHashMap<String, Object> content = ((ConcurrentHashMap<String, Object>)(notification.result));
            System.out.println("New created document notification: " + content);
          }).get();
      System.out.println("Successfully subscribed!");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Writes a new document. This triggers a notification
    // sent to our subscription.
    ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<>();
    content.put("name", "John");
    content.put("birthday", "1995-11-27");
    content.put("license", "B");

    try {
      kuzzle.getDocumentController()
          .create("nyc-open-data", "yellow-taxi", content).get();
      System.out.println("New document added to the yellow-taxi collection!");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Disconnects the SDK.
    kuzzle.disconnect();
  }
}