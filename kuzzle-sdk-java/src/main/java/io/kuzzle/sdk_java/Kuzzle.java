package io.kuzzle.sdk_java;

import com.google.gson.JsonObject;
import io.kuzzle.sdk.AbstractKuzzle;
import io.kuzzle.sdk_java.CoreClasses.GObject;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;

import java.net.URISyntaxException;

public class Kuzzle extends AbstractKuzzle<JsonObject> {
    public Kuzzle(AbstractProtocol<JsonObject> networkProtocol) throws IllegalArgumentException {
        super(new GObject(), networkProtocol);
    }

    public Kuzzle(AbstractProtocol<JsonObject> networkProtocol, KuzzleOptions options) throws IllegalArgumentException {
        super(new GObject(), networkProtocol, options);
    }
}
