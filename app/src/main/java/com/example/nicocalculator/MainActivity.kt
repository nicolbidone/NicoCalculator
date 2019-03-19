package com.example.nicocalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_results.text = "Result"
        text_operations.text = "Operations"

        button_0.setOnClickListener {
            text_results.text = "pressed"
        }

    }
}
