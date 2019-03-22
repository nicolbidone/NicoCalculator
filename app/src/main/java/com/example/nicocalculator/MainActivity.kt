package com.example.nicocalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.nicocalculator.mvp.model.CalculatorModel
import com.example.nicocalculator.mvp.presenter.Presenter
import com.example.nicocalculator.mvp.view.KeyView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        const val OPERATIONS_DEFAULT_TEXT = "Operations"
        const val RESULTS_DEFAULT_TEXT = "Results"
    }

    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(CalculatorModel(RESULTS_DEFAULT_TEXT, OPERATIONS_DEFAULT_TEXT), KeyView(this))

        button_delete.setOnClickListener {
            presenter.onDeleteButtonPressed()
        }
        button_erase.setOnClickListener {
            presenter.onEraseButtonPressed()
        }

        button_equal.setOnClickListener {
            presenter.onEqualButtonPressed()
        }

        val buttons = mutableListOf<Button>(
            button_0, button_1, button_2, button_3, button_4,
            button_5, button_6, button_7, button_8, button_9,
            button_close_par, button_open_par, button_minus, button_plus,
            button_mult, button_div, button_dot
        )

        for (button in buttons) {
            button.setOnClickListener {
                presenter.onKeyButtonPressed(button)
            }
        }
    }
}

/*
override fun onResume() {
    super.onResume()
    //presenter?.register()
}

override fun onPause() {
    super.onPause()
    //presenter?.unregister()
}
*/