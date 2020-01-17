import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.Options.KuzzleOptions;

public class SnippetTest {
  public static void main(String[] argv) {
    try {
      [snippet-code]
      System.out.println("Success");
    } catch (Exception e) {
      System.err.println((e.getMessage()));
    }
  }
}