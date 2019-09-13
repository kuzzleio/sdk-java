package io.kuzzle.sdk.Exceptions;

import io.kuzzle.sdk.CoreClasses.Responses.Response;

/**
 * Passed to async tasks when an API request returns an error.
 */
public class ApiErrorException extends KuzzleException {


    /**
     * Kuzzle API stack trace
     */
    protected String stack;

    /** Initializes a new instance of the ApiErrorException
     * @param response Kuzzle API Response.
     */
    public ApiErrorException(Response response) {
        super(response.error != null
                ? response.error.message
                : null,
              response.status);
        if (response.error != null) {
            this.stack = response.error.stack;
        }
    }
}
