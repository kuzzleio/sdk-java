import java.lang.System;
import io.kuzzle.sdk_java.Kuzzle;
import io.kuzzle.sdk_java.Protocol.WebSocket;
import io.kuzzle.sdk.CoreClasses.Responses.*;
import io.kuzzle.sdk.Exceptions.*;
import com.google.gson.JsonObject;

public class Main {

    public static void main(String[] args) throws Exception {
        WebSocket socket = new WebSocket("kuzzle");
        Kuzzle kuzzle = new Kuzzle(socket);

        kuzzle.connect();
        [snippet-code]
        kuzzle.disconnect();
    }

}