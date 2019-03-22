package com.example.nicocalculator.bus.observers

import com.example.nicocalculator.bus.observers.BasicObservers.BusObserver

abstract class DeleteButtonObserver() :
    BusObserver<DeleteButtonObserver.DeleteButtonPressed>(DeleteButtonPressed::class.java) {
    class DeleteButtonPressed
}