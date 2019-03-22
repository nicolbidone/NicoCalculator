package com.example.nicocalculator.mvp.model

import com.example.nicocalculator.Key

class CalculatorModel {

    private var textResult = RESULTS_DEFAULT_TEXT
    private var textOperations = OPERATIONS_DEFAULT_TEXT

    companion object {
        internal const val OPERATIONS_DEFAULT_TEXT = "Operations"
        internal const val RESULTS_DEFAULT_TEXT = "Results"
        internal const val DECIMAL_SEPARATOR = '.'
        internal const val VALUE_MIN_CAR = 48
        internal const val VALUE_MAX_CHAR = 57
        internal const val VALUE_EMPTY_TEXT = ""
        internal const val VALUE_ZERO = 0
        internal const val ADD_OPERATOR = "+"
        internal const val SUBTRACT_OPERATOR = "-"
        internal const val MULTIPLY_OPERATOR = "*"
        internal const val DIVIDE_OPERATOR = "/"
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

fun getItems(items: CharSequence): List<Key> {
    var res = mutableListOf<Key>()
    var num = CalculatorModel.VALUE_EMPTY_TEXT
    for (car in items) {
        if ((car.toInt() < CalculatorModel.VALUE_MIN_CAR || car.toInt() > CalculatorModel.VALUE_MAX_CHAR)
            && car.toInt() != CalculatorModel.DECIMAL_SEPARATOR.toInt()
        ) {
            res.add(Key(num))
            num = CalculatorModel.VALUE_EMPTY_TEXT
            res.add(Key(car.toString()))
        } else num += car.toString()
    }
    if (num != CalculatorModel.VALUE_EMPTY_TEXT) res.add(Key(num))
    return res
}

fun getResults(itemList: List<Key>): String {
    var textResults = CalculatorModel.VALUE_EMPTY_TEXT
    var valeAnt = mutableListOf<Key>()

    for (vale in itemList) {
        textResults += "$vale "
        if (valeAnt.size < 2) valeAnt.add(Key(vale.getValue()))
        else {
            var aux = CalculatorModel.VALUE_ZERO
            when (valeAnt[1].getValue()) {
                CalculatorModel.ADD_OPERATOR -> aux = valeAnt[0].getInt() + vale.getInt()
                CalculatorModel.DIVIDE_OPERATOR -> aux = valeAnt[0].getInt() / vale.getInt()
                CalculatorModel.MULTIPLY_OPERATOR -> aux = valeAnt[0].getInt() * vale.getInt()
                CalculatorModel.SUBTRACT_OPERATOR -> aux = valeAnt[0].getInt() - vale.getInt()
            }
            textResults += " = $aux\n$aux "
            valeAnt = mutableListOf()
            valeAnt.add(Key(aux.toString()))
        }
    }
    return textResults
}