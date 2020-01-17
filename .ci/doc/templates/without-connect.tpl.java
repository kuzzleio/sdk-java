import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;

public class SnippetTest {
  public static void main(String[] argv) {
    try {
      Kuzzle kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      [snippet-code]
      System.out.println("Success");
      kuzzle.disconnect();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
