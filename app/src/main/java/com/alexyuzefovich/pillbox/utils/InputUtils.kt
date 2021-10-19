package com.alexyuzefovich.pillbox.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    clearFocus()
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideKeyboard() {
    currentFocus?.hideKeyboard()
}