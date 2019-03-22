package com.example.nicocalculator.mvp.view

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*

class KeyView(private val activity: Activity) {

    fun setTextResult(string: String) {
        activity.text_results.text = string
    }

    fun setTextOperations(string: String) {
        activity.text_operations.text = string
    }

}

/*fun deleteButtonPressed() {
        RxBus.post(DeleteButtonObserver.DeleteButtonPressed())
    }

    fun equalButtonPressed() {
        RxBus.post(EqualButtonObserver.EqualButtonPressed())
    }

    fun eraseButtonPressed() {
        RxBus.post(EraseButtonObserver.EraseButtonPressed())
    }

    fun keyButtonPressed() {
        RxBus.post(KeyButtonObserver.KeyButtonPressed())
    }*/