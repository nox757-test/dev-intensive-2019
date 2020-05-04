package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val view = this.currentFocus
    if (view != null) {
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}