package io.kuzzle.sdk.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Task<T> {
    private CountDownLatch countDownLatch;
    private CompletableFuture<T> future;
    private AtomicReference<T> object = null;

    public Task() {
        countDownLatch = new CountDownLatch(1);
        future = CompletableFuture.<T>supplyAsync(() -> {
            try {
                countDownLatch.await();

                return object.get();
            } catch (InterruptedException e) {

            }
            return null;
        });
    }

    public CompletableFuture<T> getFuture() {
        return future;
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
