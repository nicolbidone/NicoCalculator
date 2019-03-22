package com.example.nicocalculator.mvp.model

class CalculatorModel(private var text_result: String, private var text_operations: String) {

    fun setTextResult(string: String) {
        text_result = string
    }

    fun setTextOperations(string: String) {
        text_operations = string
    }

    fun getTextOperations(): String {
        return text_operations
    }
}