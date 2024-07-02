package com.github.funczz.kotlin.rocket_launcher.android.worker

import androidx.work.OneTimeWorkRequest
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

    fun request(request: OneTimeWorkRequest? = null) {
        _request = Optional.ofNullable(request)
    }

    fun breakNow() {
        if (!_request.isPresent) return
        _isBreak = true
    }

    override fun toString(): String {
        return "CountingState(request=$request, isBreak=$isBreak)"
    }

}
