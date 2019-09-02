package io.kuzzle.unittests.EventsTest;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventListenerTests {

    @Test
    public void registerRunnableTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();
        eventListener.applyMock();

        eventListener.register(() -> {});

        verify(
                eventListener.mockedCallbacks,
                times(1)
        ).add(any(Runnable.class));
    }

    @Test
    public void unregisterRunnableTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();
        eventListener.applyMock();

        eventListener.unregister(() -> {});

        verify(
                eventListener.mockedCallbacks,
                times(1)
        ).remove(any(Runnable.class));
    }

    @Test
    public void registerConsumerTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();
        eventListener.applyMock();

        eventListener.register((obj) -> {});

        verify(
                eventListener.mockedCallbacks,
                times(1)
        ).add(any(Consumer.class));
    }

    @Test
    public void unregisterConsumerTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();
        eventListener.applyMock();

        eventListener.unregister((obj) -> {});

        verify(
                eventListener.mockedCallbacks,
                times(1)
        ).remove(any(Consumer.class));
    }

    @Test
    public void registerDuplicateTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();

        Runnable runnable1 = () -> {};
        eventListener.register(runnable1);
        eventListener.register(runnable1);
        eventListener.register(runnable1);
        eventListener.register(runnable1);
        eventListener.register(runnable1);
        eventListener.register(() -> {});

        Assert.assertEquals(2, eventListener.getCallbacks().size());
    }

    @Test
    public void triggerRunnableTest() {
        TestableEventListener<Object>
                eventListener = new TestableEventListener<>();

        AtomicBoolean success = new AtomicBoolean(false);
        eventListener.register(() -> success.set(true));

        Assert.assertFalse(success.get());

        eventListener.trigger();

        Assert.assertTrue(success.get());
    }

    @Test
    public void triggerConsumerTest() {
        TestableEventListener<String>
                eventListener = new TestableEventListener<>();

        AtomicBoolean success = new AtomicBoolean(false);
        eventListener.register((str) -> success.set(str.equals("foobar")));

        Assert.assertFalse(success.get());

        eventListener.trigger("foobar");

        Assert.assertTrue(success.get());
    }

    @Test
    public void triggerOnlyConsumerTest() {
        TestableEventListener<String>
                eventListener = new TestableEventListener<>();

        AtomicBoolean consumerSuccess = new AtomicBoolean(false);
        AtomicBoolean runnableSuccess = new AtomicBoolean(false);

        eventListener.register((str) -> consumerSuccess.set(str.equals("foobar")));
        eventListener.register(() -> runnableSuccess.set(true));

        Assert.assertFalse(consumerSuccess.get());
        Assert.assertFalse(runnableSuccess.get());

        eventListener.trigger("foobar");

        Assert.assertTrue(consumerSuccess.get());
        Assert.assertFalse(runnableSuccess.get());
    }

    @Test
    public void triggerOnlyRunnableTest() {
        TestableEventListener<String>
                eventListener = new TestableEventListener<>();

        AtomicBoolean consumerSuccess = new AtomicBoolean(false);
        AtomicBoolean runnableSuccess = new AtomicBoolean(false);

        eventListener.register(
                (str) -> consumerSuccess.set(str.equals("foobar"))
        );
        eventListener.register(() -> runnableSuccess.set(true));

        Assert.assertFalse(consumerSuccess.get());
        Assert.assertFalse(runnableSuccess.get());

        eventListener.trigger();

        Assert.assertFalse(consumerSuccess.get());
        Assert.assertTrue(runnableSuccess.get());
    }

}
