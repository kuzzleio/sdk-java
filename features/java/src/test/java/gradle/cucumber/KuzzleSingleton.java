package gradle.cucumber;

import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.WebSocket;

public class KuzzleSingleton {
    private static Kuzzle kuzzle = null;

    public static Kuzzle getInstance() {
        if (kuzzle != null) {
            return kuzzle;
        }

        kuzzle = new Kuzzle(new WebSocket((System.getenv().get("KUZZLE_HOST") != null) ? (System.getenv().get("KUZZLE_HOST")) : "localhost"));
        return kuzzle;
    }
}
