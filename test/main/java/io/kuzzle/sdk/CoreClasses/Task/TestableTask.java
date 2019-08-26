package main.java.io.kuzzle.sdk.CoreClasses.Task;

import io.kuzzle.sdk.CoreClasses.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Mockito.mock;

public class TestableTask <T> extends Task {
    public CountDownLatch mockedCountDownLatch = mock(CountDownLatch.class);
    public CompletableFuture<T> mockedFuture = mock(CompletableFuture.class);

    public TestableTask() {
        super();
    }

    public void applyMockCountDownLatch() {
        super.countDownLatch = mockedCountDownLatch;
    }

    public void applyMockFuture() {
        super.future = mockedFuture;
    }

    public CountDownLatch getCountDownLatch() {
        return super.countDownLatch;
    }

    public AtomicReference<T> getAtomicReference() {
        return super.atomicReference;
    }


}
