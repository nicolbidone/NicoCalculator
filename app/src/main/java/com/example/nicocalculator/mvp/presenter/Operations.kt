package com.example.nicocalculator.mvp.presenter

internal const val DECIMAL_SEPARATOR = '.'
internal const val VALUE_MIN_CAR = 48
internal const val VALUE_MAX_CHAR = 57
internal const val VALUE_SIZE_OPERATION = 3
internal const val VALUE_EMPTY_TEXT = ""
internal const val VALUE_ZERO = 0
internal const val ADD_OPERATOR = "+"
internal const val SUBTRACT_OPERATOR = "-"
internal const val MULTIPLY_OPERATOR = "*"
internal const val DIVIDE_OPERATOR = "/"
internal const val OPEN_PAR = "("
internal const val CLOSE_PAR = ")"

internal fun getItems(items: CharSequence): List<Key> {
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

internal fun getResults(itemList: List<Key>): String {
    var textResults = VALUE_EMPTY_TEXT
    textResults += doIt(itemList)
    return textResults
}

private fun doIt(itemList: List<Key>): String {
    var textResults: String
    var valeAnt = mutableListOf<Key>()
    var valeIn = mutableListOf<Key>()
    var i = VALUE_ZERO
    while (i < itemList.lastIndex) {
        val vale = itemList[i]
        if (vale.getValue() == OPEN_PAR) {
            while (vale.getValue() != CLOSE_PAR) {
                valeIn.add(vale)
                i++
            }
            valeAnt.add(Key(doIt(valeIn)))
        }
        when {
            valeAnt.size < VALUE_SIZE_OPERATION -> valeAnt.add(vale)
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
    var aux = VALUE_ZERO
    when (valeAnt[1].getValue()) {
        ADD_OPERATOR -> aux = valeAnt[0].getInt() + valeAnt[2].getInt()
        DIVIDE_OPERATOR -> aux = valeAnt[0].getInt() / valeAnt[2].getInt()
        MULTIPLY_OPERATOR -> aux = valeAnt[0].getInt() * valeAnt[2].getInt()
        SUBTRACT_OPERATOR -> aux = valeAnt[0].getInt() - valeAnt[2].getInt()
    }
    return aux
}