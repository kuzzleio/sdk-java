import java.lang.System;
import io.kuzzle.sdk_java.Kuzzle;
import io.kuzzle.sdk_java.Protocol.WebSocket;

public class Main {

    public static void main(String[] args) throws Exception {
        [snippet-code]
        kuzzle.disconnect();
        System.out.println("Success");
    }

}