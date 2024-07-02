package com.github.funczz.kotlin.rocket_launcher.android

import com.github.funczz.kotlin.rocket_launcher.android.activity.ready.ReadyActivity
import com.github.funczz.kotlin.rocket_launcher.android.worker.CountingState
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel

data class UiState(

    val input: String = "",

    val output: String = "",

    val samModel: RocketLauncherSamModel = RocketLauncherSamModel(),

    val currentActivityClass: Class<*> = ReadyActivity::class.java,

    val events: List<UiEvent> = emptyList(),

    val countingState: CountingState = CountingState(),

    val isAndroidTest: Boolean = false,

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
