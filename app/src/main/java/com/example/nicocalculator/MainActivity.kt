package com.example.nicocalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val setInitValueOperations = "Operations"
    private val setInitValueResults = "Results"
    private val setValuePressed = "Pressed"
    private val setValueEmpty = "Empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_results.text = setInitValueResults
        text_operations.text = setInitValueOperations

        button_0.setOnClickListener {
            text_results.text = setValuePressed
        }

        button_delete.setOnClickListener {
            text_results.text = setValueEmpty
        }

    }
}
