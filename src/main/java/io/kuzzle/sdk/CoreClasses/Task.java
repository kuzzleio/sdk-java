package io.kuzzle.sdk.CoreClasses;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Task<T> {
    protected CountDownLatch countDownLatch;
    protected CompletableFuture<T> future;
    protected AtomicReference<T> object = null;

    public Task() {
        object = new AtomicReference<T>();
        countDownLatch = new CountDownLatch(1);
        future = CompletableFuture.<T>supplyAsync(() -> {
            try {
                countDownLatch.await();

                return object.get();
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

    public boolean isCanceled() {
        return future.isCancelled();
    }

    public boolean isDone() {
        return future.isDone();
    }

    public boolean isCompletedExceptionally() {
        return future.isCompletedExceptionally();
    }



    public void trigger() {
        if (countDownLatch.getCount() > 0) {
            countDownLatch.countDown();
        }
    }

    public void trigger(T object) {
        if (countDownLatch.getCount() > 0) {
            this.object.set(object);
            countDownLatch.countDown();
        }
    }
}
