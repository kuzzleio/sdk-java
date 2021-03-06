import io.kuzzle.sdk.Kuzzle;
import java.util.ArrayList;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Options.KuzzleOptions;
import java.util.concurrent.ConcurrentHashMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;
import io.kuzzle.sdk.CoreClasses.SearchResult;
import io.kuzzle.sdk.Options.SubscribeOptions;
import io.kuzzle.sdk.Options.UpdateOptions;
import io.kuzzle.sdk.Options.CreateOptions;
import io.kuzzle.sdk.Options.SearchOptions;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
      [snippet-code]
      System.out.println("Success");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}