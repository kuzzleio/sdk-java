import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Options.KuzzleOptions;
import java.util.concurrent.ConcurrentHashMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
      [snippet-code]
      System.out.println("Error");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}