package io.kuzzle.sdk.API.Controllers;

import io.kuzzle.sdk.Kuzzle;

public class BaseController {
    protected final Kuzzle kuzzle;

    protected BaseController(Kuzzle kuzzle) {
        this.kuzzle = kuzzle;
    }
}
