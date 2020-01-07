import java.lang.System;
import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.Protocol.WebSocket;
import io.kuzzle.sdk.CoreClasses.Responses.*;
import io.kuzzle.sdk.Exceptions.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        WebSocket socket = new WebSocket("kuzzle");
        Kuzzle kuzzle = new Kuzzle(socket);

        kuzzle.connect();
        [snippet-code]
        System.out.println("Success");
    }

}