import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      [snippet-code]
      System.out.println("Success");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}
