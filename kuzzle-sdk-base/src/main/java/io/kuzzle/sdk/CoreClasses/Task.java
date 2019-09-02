package io.kuzzle.sdk.CoreClasses;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param <T> The object type that the task return.
 */
public class Task<T> {
    /**
     * A countDownLatch used to lock the future.
     */
    protected CountDownLatch countDownLatch;
    /**
     * A completable future.
     */
    protected CompletableFuture<T> future;

    /**
     * The object instance to return.
     */
    protected AtomicReference<T> atomicReference;


    /**
     * Initializes a new instance of the Task.
     */
    public Task() {
        atomicReference = new AtomicReference<T>();
        countDownLatch = new CountDownLatch(1);
        future = CompletableFuture.<T>supplyAsync(() -> {
            try {
                countDownLatch.await();

                return atomicReference.get();
            } catch (InterruptedException e) {
                return null;
            }
        });
    }

    /**
     * @return The associated CompletableFuture.
     */
    public CompletableFuture<T> getFuture() {
        return future;
    }

    /** Set the exception of the CompletableFuture.
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

    /** Set if the future is cancelled.
     * @param state
     */
    public void setCancelled(boolean state) {
        future.cancel(state);
    }


    /**
     * Unlock the future.
     */
    public void trigger() {
        countDownLatch.countDown();
    }

    /** Unlock the future and set the object.
     * @param object
     */
    public void trigger(T object) {
        atomicReference.set(object);
        countDownLatch.countDown();
    }
}
