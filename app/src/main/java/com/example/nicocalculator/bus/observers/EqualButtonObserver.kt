package com.example.nicocalculator.bus.observers

import com.example.nicocalculator.bus.observers.BasicObservers.BusObserver

abstract class EqualButtonObserver :
    BusObserver<EqualButtonObserver.EqualButtonPressed>(EqualButtonPressed::class.java) {
    class EqualButtonPressed
}