package com.example.nicocalculator.mvp.model

import com.example.nicocalculator.Key

open class CalculatorModel {

    private var textResult = RESULTS_DEFAULT_TEXT
    private var textOperations = "8*9+6*3*3+5"

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
    textResults += doIt(itemList)
    return textResults
}

fun doIt(itemList: List<Key>): String {
    var textResults: String
    var valeAnt = mutableListOf<Key>()
    var valeIn = mutableListOf<Key>()
    var i = 0
    while (i < itemList.lastIndex) {
        val vale = itemList[i]
        if (vale.getValue() == "("){
            while (vale.getValue() != ")") {
                valeIn.add(vale)
                i++
            }
            valeAnt.add(Key(doIt(valeIn)))
        }
        when {
            valeAnt.size < 3 -> valeAnt.add(vale)
            valeAnt[1].getPrecendence() > vale.getPrecendence() -> {
                valeAnt.add(vale)
                val aux = threeItemsOperation(valeAnt)
                valeAnt = mutableListOf()
                valeAnt.add(Key(aux.toString()))
                valeAnt.add(vale)
            }
            else -> {
                var valeAux = mutableListOf<Key>()
                valeAux.add(valeAnt[valeAnt.lastIndex])
                valeAux.add(vale)
                i++
                valeAux.add(itemList[i])
                val aux = threeItemsOperation(valeAux)
                valeAnt.removeAt(valeAnt.lastIndex)
                valeAnt.add(Key(aux.toString()))
            }
        }
        i++
    }
    textResults = if (valeAnt.size > 2) threeItemsOperation(valeAnt).toString() else valeAnt[0].toString()
    return textResults
}

private fun threeItemsOperation(
    valeAnt: MutableList<Key>
): Int {
    var aux = CalculatorModel.VALUE_ZERO
    when (valeAnt[1].getValue()) {
        CalculatorModel.ADD_OPERATOR -> aux = valeAnt[0].getInt() + valeAnt[2].getInt()
        CalculatorModel.DIVIDE_OPERATOR -> aux = valeAnt[0].getInt() / valeAnt[2].getInt()
        CalculatorModel.MULTIPLY_OPERATOR -> aux = valeAnt[0].getInt() * valeAnt[2].getInt()
        CalculatorModel.SUBTRACT_OPERATOR -> aux = valeAnt[0].getInt() - valeAnt[2].getInt()
    }
    return aux
}


