package io.kuzzle.runner;

import io.kuzzle.runner.Helpers.IJObjectHelper;
import io.kuzzle.sdk.AbstractKuzzle;
import io.kuzzle.sdk.CoreClasses.Json.IJObject;
import io.kuzzle.sdk.Options.KuzzleOptions;
import io.kuzzle.sdk.Protocol.AbstractProtocol;

import java.net.URISyntaxException;

public class Kuzzle<T> extends AbstractKuzzle<T> {
    public Kuzzle(AbstractProtocol<T> networkProtocol)
            throws IllegalArgumentException {
        super((IJObject<T>)IJObjectHelper.newIJObject(), networkProtocol);
    }

    public Kuzzle(AbstractProtocol<T> networkProtocol, KuzzleOptions options) throws IllegalArgumentException {
        super((IJObject<T>) IJObjectHelper.newIJObject(), networkProtocol, options);
    }
}
