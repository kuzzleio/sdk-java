package io.kuzzle.test.CoreClasses.TaskTest;

import io.kuzzle.sdk.CoreClasses.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TaskTests {
  @Test
  public void constructorTest() {
    TestableTask<Object> task = new TestableTask<>();
    Assert.assertNotNull(task.getFuture());
  }

  @Test
  public void getFutureTest() {
    Task<Object> task = new Task<>();

    Assert.assertEquals(CompletableFuture.class, task.getFuture().getClass());
  }

  @Test
  public void setExceptionTest() {
    TestableTask<Object> task = new TestableTask<>();
    task.applyMocks();

    task.setException(new Exception("foobar"));

    verify(task.mockedFuture, times(1)).completeExceptionally(any(Exception.class));
  }

  @Test
  public void isCancelledTest() {
    TestableTask<Object> task = new TestableTask<>();
    task.applyMocks();

    when(task.mockedFuture.isCancelled()).thenReturn(true);

    Assert.assertTrue(task.isCancelled());

    verify(task.mockedFuture, times(1)).isCancelled();
  }

  @Test
  public void isDoneTest() {
    TestableTask<Object> task = new TestableTask<>();
    task.applyMocks();

    when(task.mockedFuture.isDone()).thenReturn(true);

    Assert.assertTrue(task.isDone());

    verify(task.mockedFuture, times(1)).isDone();
  }

  @Test
  public void isCompletedExceptionallyTest() {
    TestableTask<Object> task = new TestableTask<>();
    task.applyMocks();

    when(task.mockedFuture.isCompletedExceptionally()).thenReturn(true);

    Assert.assertTrue(task.isCompletedExceptionally());

    verify(task.mockedFuture, times(1)).isCompletedExceptionally();
  }

  @Test
  public void setCancelledTest() {
    TestableTask<Object> task = new TestableTask<>();
    task.applyMocks();

    task.setCancelled(true);

    verify(task.mockedFuture, times(1)).cancel(anyBoolean());
  }

  @Test
  public void triggerTest() {
    Task task = new Task();

    final AtomicBoolean success = new AtomicBoolean(false);

    CompletableFuture taskChain = task.getFuture().thenRun(() -> success.set(true));

    task.trigger();

    taskChain.join();

    Assert.assertTrue(success.get());
  }

  @Test
  public void triggerWithObjectTest() {
    Task<String> task = new Task();

    final AtomicBoolean success = new AtomicBoolean(false);

    CompletableFuture taskChain = task.getFuture().thenAccept((str) -> success.set(str.equals("foobar")));

    task.trigger("foobar");

    taskChain.join();

    Assert.assertTrue(success.get());
  }
}
