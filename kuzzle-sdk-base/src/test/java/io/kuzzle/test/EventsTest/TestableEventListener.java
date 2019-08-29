package io.kuzzle.test.EventsTest;

import io.kuzzle.sdk.Events.EventListener;

import java.util.Set;

import static org.mockito.Mockito.mock;

public class TestableEventListener <T> extends EventListener {
    public Set<Object> mockedCallbacks = mock(Set.class);
    public TestableEventListener() {
        super();
    }

    public void applyMock() {
        super.callbacks = mockedCallbacks;
    }

    public Set<Object> getCallbacks() {
        return super.callbacks;
    }
}
