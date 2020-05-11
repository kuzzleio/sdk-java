package io.kuzzle.sdk.CoreClasses;

import java.util.concurrent.CompletableFuture;

/**
 * @param <T> The object type that the task return.
 */
public class Task<T> {
  /**
   * A completable future.
   */
  protected CompletableFuture<T> future = new CompletableFuture<>();

  /**
   * @return The associated CompletableFuture.
   */
  public CompletableFuture<T> getFuture() {
    return future;
  }

  /**
   * Set the exception of the CompletableFuture.
   *
   * @param exception
   */
  public void setException(Exception exception) {
    future.completeExceptionally(exception);
  }

  /**
   * @return true if future is cancelled.
   */
  public boolean isCancelled() {
    return future.isCancelled();
  }

  /**
   * @return true if the future is done.
   */
  public boolean isDone() {
    return future.isDone();
  }

  /**
   * @return true if the future has been completed exceptionally.
   */
  public boolean isCompletedExceptionally() {
    return future.isCompletedExceptionally();
  }

  /**
   * Set if the future is cancelled.
   *
   * @param state
   */
  public void setCancelled(boolean state) {
    future.cancel(state);
  }

  /**
   * Unlock the future.
   */
  public void trigger() {
    future.complete(null);
  }

  /**
   * Unlock the future and set the object.
   *
   * @param object
   */
  public void trigger(T object) {
    future.complete(object);
  }
}
