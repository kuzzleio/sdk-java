import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.Protocol.WebSocketOptions;
import io.kuzzle.sdk.Options.KuzzleOptions;
import java.util.concurrent.ConcurrentHashMap;
import io.kuzzle.sdk.CoreClasses.Responses.Response;

public class SnippetTest {
  public static void main(String[] argv) {
    try {
      Kuzzle kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
      [snippet-code]
      System.out.println("Success");
      kuzzle.disconnect();
    } catch (Exception e) {
      System.err.println((e.getMessage()));
    }
  }
}