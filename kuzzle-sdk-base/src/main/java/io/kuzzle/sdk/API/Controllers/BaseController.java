package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.AbstractKuzzle;

public class BaseController<T> {
    protected final AbstractKuzzle<T> kuzzle;

    protected BaseController(AbstractKuzzle<T> kuzzle) {
        this.kuzzle = kuzzle;
    }
}
