package com.syrgdev.testapplicationforpikabu.common.view.base;

import com.syrgdev.testapplicationforpikabu.common.observable.IObservable;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseObservableView<OBSERVER_TYPE> extends BaseView implements IObservable<OBSERVER_TYPE> {

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


    // Get a reference to the unmodifiable set containing all the registered observers.
    public final Set<OBSERVER_TYPE> getObservers() {
        return Collections.unmodifiableSet(mObservers);
    }
}