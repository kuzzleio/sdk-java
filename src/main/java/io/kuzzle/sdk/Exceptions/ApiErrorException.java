package io.kuzzle.sdk.Exceptions;

import io.kuzzle.sdk.CoreClasses.Response.Response;

public class ApiErrorException extends KuzzleException {
    public ApiErrorException(Response response) {
        super(response.error != null
                ? response.error.message
                : null,
              response.status);
    }
}
