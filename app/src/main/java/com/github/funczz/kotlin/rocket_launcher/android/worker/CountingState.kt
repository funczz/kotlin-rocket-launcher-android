package com.github.funczz.kotlin.rocket_launcher.android.worker

import android.content.Context
import androidx.work.OneTimeWorkRequest
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import java.util.Optional

class CountingState {

    private var _request: Optional<OneTimeWorkRequest> = Optional.empty()

    private var _isBreak: Boolean = false

    val request: Optional<OneTimeWorkRequest>
        get() = _request

    val isBreak: Boolean
        get() = _isBreak

    fun initialize() {
        _request = Optional.empty()
        _isBreak = false
    }

    fun enqueueWork(uiState: UiState, context: Context) {
        if (uiState.isAndroidTest) return
        if (_request.isPresent) return
        if (_isBreak) return
        val req = CountingWorker.newRequest()
        _request = Optional.of(req)
        CountingWorker.enqueueUniqueWork(context = context, request = req)
    }

    fun cancelWork(context: Context) {
        if (!_request.isPresent) return
        _isBreak = true
        CountingWorker.cancelWork(context = context, request = _request.get())
    }

    override fun toString(): String {
        return "CountingState(request=$request, isBreak=$isBreak)"
    }

}
