package com.example.nicocalculator.bus.observers

import com.example.nicocalculator.bus.observers.BasicObservers.BusObserver

abstract class EraseButtonObserver :
    BusObserver<EraseButtonObserver.EraseButtonPressed>(EraseButtonPressed::class.java) {
    class EraseButtonPressed
}