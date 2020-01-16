import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;

public class SnippetTest {
  public static void main(String[] argv) {
    try {
      Kuzzle kuzzle = new Kuzzle(new WebSocket("localhost"));
      [snippet-code]
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
