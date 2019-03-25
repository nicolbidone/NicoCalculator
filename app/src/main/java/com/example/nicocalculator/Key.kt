package com.example.nicocalculator

import com.example.nicocalculator.mvp.model.CalculatorModel

data class Key(private var operation: String) {

    private var precendence = 0

    init {
        precendence = precedenceVal(operation)
    }

    fun getValue(): String {
        return operation
    }

    fun getInt(): Int {
        return operation.toInt()
    }

    override fun toString(): String {
        return operation
    }

    fun getPrecendence(): Int {
        return precendence
    }

    private fun precedenceVal(string: String): Int {
        return when (string) {
            CalculatorModel.MULTIPLY_OPERATOR -> 2
            CalculatorModel.DIVIDE_OPERATOR -> 2
            CalculatorModel.ADD_OPERATOR -> 1
            CalculatorModel.SUBTRACT_OPERATOR -> 1
            else -> 0
        }
    }
}