package com.example.nicocalculator.mvp.presenter

import android.widget.Button
import com.example.nicocalculator.Key
import com.example.nicocalculator.MainActivity
import com.example.nicocalculator.mvp.model.CalculatorModel
import com.example.nicocalculator.mvp.view.KeyView

class Presenter(private val model: CalculatorModel, private val view: KeyView) {

    companion object {
        private const val VALUE_EMPTY_TEXT = ""
        private const val VALUE_ZERO = 0
        private const val DECIMAL_SEPARATOR = '.'
        private const val VALUE_MIN_CAR = 48
        private const val VALUE_MAX_CHAR = 57
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
        if (textOperations == MainActivity.OPERATIONS_DEFAULT_TEXT) {
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
                    "+" -> aux = valeant[0].getInt() + vale.getInt()
                    "/" -> aux = valeant[0].getInt() / vale.getInt()
                    "*" -> aux = valeant[0].getInt() * vale.getInt()
                    "-" -> aux = valeant[0].getInt() - vale.getInt()
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

/*fun unregister() {
    val activity = view.activity
    RxBus.clear(activity)
}

fun register() {
    val activity = view.activity
    RxBus.subscribe(activity, object : DeleteButtonObserver() {
        override fun onEvent(value: DeleteButtonObserver.DeleteButtonPressed) {
            onDeleteButtonPressed()
        }
    })
    RxBus.subscribe(activity, object : KeyButtonObserver() {
        override fun onEvent(value: KeyButtonObserver.KeyButtonPressed) {
            onKeyButtonPressed()
        }
    })
    RxBus.subscribe(activity, object : EqualButtonObserver() {
        override fun onEvent(value: EqualButtonObserver.EqualButtonPressed) {
            onEqualButtonPressed()
        }
    })
    RxBus.subscribe(activity, object : EraseButtonObserver() {
        override fun onEvent(value: EraseButtonObserver.EraseButtonPressed) {
            onEraseButtonPressed()
        }
    })
    RxBus.clear(activity)
}*/