package com.example.nicocalculator

data class Key(var operation: String) {

    fun getValue(): String {
        return operation
    }

    fun getInt(): Int {
        return operation.toInt()
    }

    override fun toString(): String {
        return operation
    }
}