package com.example.nicocalculator.mvp.model

import com.example.nicocalculator.Utils.*

class CalculatorModel {

    private var textResult = RESULTS_DEFAULT_TEXT
    private var textOperations = OPERATIONS_DEFAULT_TEXT

    fun setTextResult(string: String) {
        textResult = string
    }

    fun getTextResult(): String {
        return getResults(getItems(getTextOperations()))
    }

    fun setTextOperations(string: String) {
        textOperations = string
    }

    fun getTextOperations(): String {
        return textOperations
    }

    private fun getItems(items: CharSequence): List<KeyUtils> {
        var res = mutableListOf<KeyUtils>()
        var num = VALUE_EMPTY_TEXT
        for (car in items) {
            if ((car.toInt() < VALUE_MIN_CAR || car.toInt() > VALUE_MAX_CHAR)
                && car.toInt() != DECIMAL_SEPARATOR.toInt()
            ) {
                res.add(KeyUtils(num))
                num = VALUE_EMPTY_TEXT
                res.add(KeyUtils(car.toString()))
            } else num += car.toString()
        }
        if (num != VALUE_EMPTY_TEXT) res.add(
            KeyUtils(
                num
            )
        )
        return res
    }

    private fun getResults(itemList: List<KeyUtils>): String {
        var textResults = VALUE_EMPTY_TEXT
        textResults += doIt(itemList)
        return textResults
    }

    private fun doIt(itemList: List<KeyUtils>): String {
        var textResults: String
        var valeAnt = mutableListOf<KeyUtils>()
        var valeIn = mutableListOf<KeyUtils>()
        var i = VALUE_ZERO
        while (i < itemList.lastIndex) {
            val vale = itemList[i]
            if (vale.getValue() == OPEN_PAR) {
                while (vale.getValue() != CLOSE_PAR) {
                    valeIn.add(vale)
                    i++
                }
                valeAnt.add(KeyUtils(doIt(valeIn)))
            }
            when {
                valeAnt.size < VALUE_SIZE_OPERATION -> valeAnt.add(vale)
                valeAnt[1].getPrecendence() > vale.getPrecendence() -> {
                    valeAnt.add(vale)
                    val aux = threeItemsOperation(valeAnt)
                    valeAnt = mutableListOf()
                    valeAnt.add(KeyUtils(aux.toString()))
                    valeAnt.add(vale)
                }
                else -> {
                    var valeAux = mutableListOf<KeyUtils>()
                    valeAux.add(valeAnt[valeAnt.lastIndex])
                    valeAux.add(vale)
                    i++
                    valeAux.add(itemList[i])
                    val aux = threeItemsOperation(valeAux)
                    valeAnt.removeAt(valeAnt.lastIndex)
                    valeAnt.add(KeyUtils(aux.toString()))
                }
            }
            i++
        }
        textResults = if (valeAnt.size > 2) threeItemsOperation(valeAnt).toString() else valeAnt[0].toString()
        return textResults
    }

    private fun threeItemsOperation(
        valeAnt: MutableList<KeyUtils>
    ): Int {
        var aux = VALUE_ZERO
        when (valeAnt[1].getValue()) {
            ADD_OPERATOR -> aux = valeAnt[0].getInt() + valeAnt[2].getInt()
            DIVIDE_OPERATOR -> aux = valeAnt[0].getInt() / valeAnt[2].getInt()
            MULTIPLY_OPERATOR -> aux = valeAnt[0].getInt() * valeAnt[2].getInt()
            SUBTRACT_OPERATOR -> aux = valeAnt[0].getInt() - valeAnt[2].getInt()
        }
        return aux
    }
}