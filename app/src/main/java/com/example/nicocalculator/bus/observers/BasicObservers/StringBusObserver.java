package com.example.nicocalculator.bus.observers.BasicObservers;

public abstract class StringBusObserver extends BusObserver<String> {
    public StringBusObserver() {
        super(String.class);
    }
}
