package com.example.nicocalculator.bus.observers.BasicObservers;

public abstract class BooleanBusObserver extends BusObserver<Boolean> {
    public BooleanBusObserver() {
        super(Boolean.class);
    }
}