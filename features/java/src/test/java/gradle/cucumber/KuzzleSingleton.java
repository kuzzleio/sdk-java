package gradle.cucumber;

import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.WebSocket;

public class KuzzleSingleton {
    private static Kuzzle kuzzle = null;
    private static WebSocket socket;

    public static Kuzzle getInstance() {
        if (kuzzle != null) {
            return kuzzle;
        }

        socket = new WebSocket((System.getenv().get("KUZZLE_HOST") != null) ? (System.getenv().get("KUZZLE_HOST")) : "localhost");
        kuzzle = new Kuzzle(socket);
        return kuzzle;
    }
}
