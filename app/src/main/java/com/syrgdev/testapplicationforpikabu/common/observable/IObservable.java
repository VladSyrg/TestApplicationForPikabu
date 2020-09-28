package com.syrgdev.testapplicationforpikabu.common.observable;

public interface IObservable<OBSERVER_TYPE> {

    void registerObserver(OBSERVER_TYPE observer);

    void unregisterObserver(OBSERVER_TYPE observer);
}
