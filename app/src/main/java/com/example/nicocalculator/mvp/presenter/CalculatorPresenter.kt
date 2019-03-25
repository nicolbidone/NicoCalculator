package com.example.nicocalculator.mvp.presenter

import android.widget.Button
import com.example.nicocalculator.Utils.OPERATIONS_DEFAULT_TEXT
import com.example.nicocalculator.Utils.VALUE_EMPTY_TEXT
import com.example.nicocalculator.mvp.model.CalculatorModel
import com.example.nicocalculator.mvp.view.CalculatorView

class CalculatorPresenter(private val model: CalculatorModel, private val view: CalculatorView) {

    fun onDeleteButtonPressed() {
        var textOperations = model.getTextOperations()
        textOperations =
            if (textOperations.isNotEmpty()) textOperations.subSequence(0 until textOperations.length - 1) as String
            else VALUE_EMPTY_TEXT
        model.setTextOperations(textOperations)
        view.setTextOperations(textOperations)
    }

    fun onEraseButtonPressed() {
        model.setTextOperations(VALUE_EMPTY_TEXT)
        view.setTextOperations(VALUE_EMPTY_TEXT)
    }

    fun onKeyButtonPressed(button: Button) {
        var textOperations = model.getTextOperations()
        if (textOperations == OPERATIONS_DEFAULT_TEXT) {
            textOperations = VALUE_EMPTY_TEXT
        }
        textOperations += button.text
        model.setTextOperations(textOperations)
        view.setTextOperations(textOperations)
    }

    fun onEqualButtonPressed() {
        var textResult = model.getTextResult()
        model.setTextResult(textResult)
        view.setTextResult(textResult)
    }
}