package com.example.nicocalculator.bus.observers.BasicObservers;

public abstract class LongBusObserver extends BusObserver<Long> {
    public LongBusObserver() {
        super(Long.class);
    }
}
