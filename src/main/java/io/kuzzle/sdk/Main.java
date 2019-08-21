package io.kuzzle.sdk;

import io.kuzzle.sdk.Exceptions.InternalException;
import io.kuzzle.sdk.Exceptions.NotConnectedException;
import io.kuzzle.sdk.Protocol.WebSocket;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Started");
        Kuzzle kuzzle = new Kuzzle(new WebSocket("localhost", 7512, true));
        kuzzle.connect();
        kuzzle.query(new JSONObject("{\"controller\":\"server\",\"action\":\"now\"}"));
    }

}
