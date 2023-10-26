package com.example.notes.models

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToastState {
    private var _showToast = MutableStateFlow(false)
    val showToast = _showToast.asStateFlow()

    var toastMessage = ""
        private set

    @OptIn(DelicateCoroutinesApi::class)
    fun setToastMessage(text: String) {
        toastMessage = text
        enableToast()
        GlobalScope.launch {
            delay(500)
            disableToast()
        }
    }

    private fun enableToast() {
        _showToast.value = true
    }

    fun disableToast() {
        _showToast.value = false
    }
}
