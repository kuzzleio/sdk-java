package main.java.io.kuzzle.sdk.CoreClasses.Task;

import io.kuzzle.sdk.CoreClasses.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TaskTests {

    @Test
    public void constructorTest() {
        TestableTask<Object> task = new TestableTask<>();
        Assert.assertNotNull(task.getAtomicReference());
        Assert.assertEquals(
                AtomicReference.class,
                task.getAtomicReference().getClass()
        );
        Assert.assertNotNull(task.getCountDownLatch());
        Assert.assertEquals(
                CountDownLatch.class,
                task.getCountDownLatch().getClass()
        );
        Assert.assertNotNull(task.getFuture());
    }

    @Test
    public void getFutureTest() {
        Task<Object> task = new Task<>();

        Assert.assertEquals(
                CompletableFuture.class,
                task.getFuture().getClass()
        );
    }

    @Test
    public void setExceptionTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockFuture();

        task.setException(new Exception("foobar"));

        verify(
                task.mockedFuture,
                times(1)
        ).completeExceptionally(any(Exception.class));
    }

    @Test
    public void isCancelledTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockFuture();

        when(task.mockedFuture.isCancelled())
                .thenAnswer(invocation -> true);

        Assert.assertTrue(task.isCancelled());

        verify(
                task.mockedFuture,
                times(1)
        ).isCancelled();
    }

    @Test
    public void isDoneTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockFuture();

        when(task.mockedFuture.isDone())
                .thenAnswer(invocation -> true);

        Assert.assertTrue(task.isDone());

        verify(
                task.mockedFuture,
                times(1)
        ).isDone();
    }

    @Test
    public void isCompletedExceptionallyTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockFuture();

        when(task.mockedFuture.isCompletedExceptionally())
                .thenAnswer(invocation -> true);

        Assert.assertTrue(task.isCompletedExceptionally());

        verify(
                task.mockedFuture,
                times(1)
        ).isCompletedExceptionally();
    }

    @Test
    public void setCancelledTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockFuture();

        task.setCancelled(true);

        verify(
                task.mockedFuture,
                times(1)
        ).cancel(anyBoolean());
    }

    @Test
    public void triggerTest() {
        TestableTask<Object> task = new TestableTask<>();
        task.applyMockCountDownLatch();

        when(task.mockedCountDownLatch.getCount())
                .thenAnswer(invocation -> 1);

        task.trigger();

        verify(
                task.mockedCountDownLatch,
                times(1)
        ).getCount();

        verify(
                task.mockedCountDownLatch,
                times(1)
        ).countDown();
    }

    @Test
    public void triggerWithObjectTest() {
        TestableTask<String> task = new TestableTask();
        task.applyMockCountDownLatch();

        when(task.mockedCountDownLatch.getCount())
                .thenAnswer(invocation -> 1);

        Object obj = new Object();
        task.trigger("foobar");

        verify(
                task.mockedCountDownLatch,
                times(1)
        ).getCount();

        Assert.assertNotNull(task.getAtomicReference().get());

        task.getAtomicReference().get().equals("foobar");

        verify(
                task.mockedCountDownLatch,
                times(1)
        ).countDown();
    }
}
