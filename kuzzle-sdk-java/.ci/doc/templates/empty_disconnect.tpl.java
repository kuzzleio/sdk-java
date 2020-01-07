import java.lang.System;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        [snippet-code]
        kuzzle.disconnect();
        System.out.println("Success");
    }

}