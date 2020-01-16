import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Protocol.AbstractProtocol;

public class SnippetTest {
  public static void main(String[] argv) {
    try {
      Kuzzle kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      [snippet-code]
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
