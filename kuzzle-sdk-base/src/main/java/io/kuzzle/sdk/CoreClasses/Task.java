package io.kuzzle.sdk.CoreClasses;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Task<T> {
    protected CountDownLatch countDownLatch;
    protected CompletableFuture<T> future;
    protected AtomicReference<T> atomicReference = null;

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

    public CompletableFuture<T> getFuture() {
        return future;
    }

    public void setException(Exception e) {
        future.completeExceptionally(e);
    }

    public boolean isCancelled() {
        return future.isCancelled();
    }

    public boolean isDone() {
        return future.isDone();
    }

    public boolean isCompletedExceptionally() {
        return future.isCompletedExceptionally();
    }

    public void setCancelled(boolean state) {
        future.cancel(state);
    }



    public void trigger() {
        countDownLatch.countDown();
    }

    public void trigger(T object) {
        atomicReference.set(object);
        countDownLatch.countDown();
    }
}
