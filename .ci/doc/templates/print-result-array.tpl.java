import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Options.SubscribeOptions;
import io.kuzzle.sdk.CoreClasses.Responses.Response;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
      [snippet-code]
      for (Object o : result.get("successes")) {
        System.out.println(o);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}