package com.example.nicocalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object {
        const val OPERATIONS_DEFAULT_TEXT = "Operations"
        const val RESULTS_DEFAULT_TEXT = "Results"
        const val SET_VALUE_PRESSED = "Pressed"
        const val SET_VALUE_EMPTY = "Empty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_results.text = RESULTS_DEFAULT_TEXT
        text_operations.text = OPERATIONS_DEFAULT_TEXT

        button_0.setOnClickListener {
            text_results.text = SET_VALUE_PRESSED
        }

        button_delete.setOnClickListener {
            text_results.text = SET_VALUE_EMPTY
        }

    }
}
