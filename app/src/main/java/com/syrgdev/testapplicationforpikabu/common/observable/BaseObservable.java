package com.syrgdev.testapplicationforpikabu.common.observable;


import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base class for observable entities in the application
 *
 * @param <OBSERVER_TYPE> the class of the observers
 */
public abstract class BaseObservable<OBSERVER_TYPE> implements IObservable<OBSERVER_TYPE> {

    // thread-safe set of observers
    private Set<OBSERVER_TYPE> mObservers = Collections.newSetFromMap(new ConcurrentHashMap<>(1));

    @Override
    public void registerObserver(OBSERVER_TYPE observer) {
        mObservers.add(observer);
    }

    @Override
    public void unregisterObserver(OBSERVER_TYPE observer) {
        mObservers.remove(observer);
    }

    /**
     * Get a reference to the unmodifiable set containing all the registered observers.
     */
    public final Set<OBSERVER_TYPE> getObservers() {
        return Collections.unmodifiableSet(mObservers);
    }
}