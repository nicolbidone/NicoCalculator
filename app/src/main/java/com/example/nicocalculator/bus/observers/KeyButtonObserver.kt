package com.example.nicocalculator.bus.observers

import com.example.nicocalculator.bus.observers.BasicObservers.BusObserver

abstract class KeyButtonObserver :
    BusObserver<KeyButtonObserver.KeyButtonPressed>(KeyButtonPressed::class.java) {
    class KeyButtonPressed
}