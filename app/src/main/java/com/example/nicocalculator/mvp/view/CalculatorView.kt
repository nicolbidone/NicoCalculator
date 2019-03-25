package com.example.nicocalculator.mvp.view

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*

open class CalculatorView(activity: Activity) : ActivityView(activity) {

    fun setTextResult(string: String) {
        activity!!.text_results.text = string
    }

    fun setTextOperations(string: String) {
        activity!!.text_operations.text = string
    }
}