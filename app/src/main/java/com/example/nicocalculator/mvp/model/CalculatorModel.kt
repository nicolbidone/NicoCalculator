package com.example.nicocalculator.mvp.model

class CalculatorModel {

    private var textResult = RESULTS_DEFAULT_TEXT
    private var textOperations = OPERATIONS_DEFAULT_TEXT

    companion object {
        const val OPERATIONS_DEFAULT_TEXT = "Operations"
        const val RESULTS_DEFAULT_TEXT = "Results"
    }

    fun setTextResult(string: String) {
        textResult = string
    }

    fun setTextOperations(string: String) {
        textOperations = string
    }

    fun getTextOperations(): String {
        return textOperations
    }
}