package com.github.funczz.kotlin.rocket_launcher.android

import androidx.work.OneTimeWorkRequest
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import java.util.Optional

data class UiState(

    val input: String = "",

    val output: String = "",

    val samModel: RocketLauncherSamModel = RocketLauncherSamModel(),

    val events: List<UiEvent> = emptyList(),

    val request: Optional<OneTimeWorkRequest> = Optional.empty(),

    val isBreak: Boolean = false,

    ) {

    @Suppress("unused")
    fun addEvent(event: UiEvent): UiState {
        return this.copy(events = UiEvent.add(event = event, events = events))
    }

    fun addEvent(payload: Any): UiState {
        return this.copy(events = UiEvent.add(payload = payload, events = events))
    }

    fun delEvent(event: UiEvent): UiState {
        return this.copy(events = UiEvent.del(event = event, events = events))
    }

}
