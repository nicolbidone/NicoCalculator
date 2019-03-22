package com.example.nicocalculator.mvp.presenter

import android.widget.Button
import com.example.nicocalculator.Key
import com.example.nicocalculator.mvp.model.CalculatorModel
import com.example.nicocalculator.mvp.view.CalculatorView

class CalculatorPresenter(private val model: CalculatorModel, private val view: CalculatorView) {

    companion object {
        private const val VALUE_EMPTY_TEXT = ""
        private const val VALUE_ZERO = 0
        private const val DECIMAL_SEPARATOR = '.'
        private const val VALUE_MIN_CAR = 48
        private const val VALUE_MAX_CHAR = 57
        private const val ADD_OPERATOR = "+"
        private const val SUBTRACT_OPERATOR = "-"
        private const val MULTIPLY_OPERATOR = "*"
        private const val DIVIDE_OPERATOR = "/"
    }

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
        if (textOperations == CalculatorModel.OPERATIONS_DEFAULT_TEXT) {
            textOperations = VALUE_EMPTY_TEXT
        }
        textOperations += button.text
        model.setTextOperations(textOperations)
        view.setTextOperations(textOperations)
    }

    fun onEqualButtonPressed() {
        var textResult = getResults(getItems(model.getTextOperations()))
        model.setTextResult(textResult)
        view.setTextResult(textResult)
    }

    private fun getResults(itemList: List<Key>): String {
        var textResults = VALUE_EMPTY_TEXT
        var valeant = mutableListOf<Key>()

        for (vale in itemList) {
            textResults += "$vale "
            if (valeant.size < 2) valeant.add(Key(vale.getValue()))
            else {
                var aux = VALUE_ZERO
                when (valeant[1].getValue()) {
                    ADD_OPERATOR -> aux = valeant[0].getInt() + vale.getInt()
                    DIVIDE_OPERATOR -> aux = valeant[0].getInt() / vale.getInt()
                    MULTIPLY_OPERATOR -> aux = valeant[0].getInt() * vale.getInt()
                    SUBTRACT_OPERATOR -> aux = valeant[0].getInt() - vale.getInt()
                }
                textResults += " = $aux\n$aux "
                valeant = mutableListOf()
                valeant.add(Key(aux.toString()))
            }
        }
        return textResults
    }

    private fun getItems(items: CharSequence): List<Key> {
        var res = mutableListOf<Key>()
        var num = VALUE_EMPTY_TEXT
        for (car in items) {
            if ((car.toInt() < VALUE_MIN_CAR || car.toInt() > VALUE_MAX_CHAR)
                && car.toInt() != DECIMAL_SEPARATOR.toInt()
            ) {
                res.add(Key(num))
                num = VALUE_EMPTY_TEXT
                res.add(Key(car.toString()))
            } else num += car.toString()
        }
        if (num != VALUE_EMPTY_TEXT) res.add(Key(num))
        return res
    }
}